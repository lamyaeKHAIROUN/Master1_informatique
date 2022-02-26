#include <iostream>
#include <atomic>
#include <mutex>
#include <mpi.h>
#include <thread>
#include "structure.h"

using namespace std;

void envoyerMessage(int type, int destination, int contenu=-1 ) {

    Message msg {type,contenu};
    MPI_Send(&msg, MSG_SIZE, MPI_INT, destination, type, MPI_COMM_WORLD);
    cout << "Le processus numero "<< processus.pid << " a envoyer " 
         << (type == typeDeMessage::REQUETE? "une requete" : "un jeton") 
         << " au processus numero " << destination << endl; 
}

Message recevoirMessage() {

    MPI_Status status;
    Message msg;
    MPI_Recv(&msg, MSG_SIZE, MPI_INT, MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD, &status);
    return msg;
}

void demanderAcces() {

    processus.estDemandeur = true;
    if (processus.pere != -1) {
        envoyerMessage(typeDeMessage::REQUETE, processus.pere, processus.pid);
        processus.pere = -1;
    }
}

void libererAcces() {

   
    processus.estDemandeur = false;
    if (processus.suivant != -1) {
        envoyerMessage(typeDeMessage::JETON, processus.suivant, processus.pid);
        processus.jetonPresent = false;
        processus.suivant = -1;
    }
}

void recevoirRequete(Message msg) {
    
    if (processus.pere == -1) {
        if (processus.estDemandeur) {
            processus.suivant = msg.from;
        }
        else {
            processus.jetonPresent = false;
            envoyerMessage(typeDeMessage::JETON, msg.from, processus.pid);
        }
    }
    else {
        envoyerMessage(typeDeMessage::REQUETE, processus.pere, msg.from);
    }
    processus.pere = msg.from;
}

void SECTION_CRITIQUE(const Processus* const proc) {

    //using namespace chrono_literals;
    demanderAcces();
     cout << "Le prcessus numero "<<processus.pid << " attend pour avoire l'acces à la ressource critique..." << "\n";  
    while (!processus.jetonPresent || !processus.estDemandeur) {}

    cout << "Processus numero " << proc->pid << " est entrer pour utiliser la ressource critique" << "\n";
    this_thread::sleep_for(1s);

    cout << "Processus numero " << proc->pid << " a quitté la ressource critique" << "\n";
    libererAcces();
}

//utilisés pour synchroniser les accès mémoire entre différents threads.
atomic_bool theEnd = false;
void lanceur() {
    while (!theEnd) {
        SECTION_CRITIQUE(&processus);
    }
}

int main(int argc, char* argv[]) {

    /* Initialiser l'infrastructure nécessaire à la communication */
    MPI_Init(&argc, &argv);

    /* Identifier ce processus */
    MPI_Comm_rank(MPI_COMM_WORLD, &processus.pid);

    /* Découvrez combien de processus totals sont actifs */
    MPI_Comm_size(MPI_COMM_WORLD, &processus.size);

    /* Jusqu'à présent, tous les programmes ont fait exactement la même chose. 
       Ici, on vérifie le pid pour distinguer les rôles des programmes */

    processus.pere = 0;
    processus.suivant = -1;
    processus.estDemandeur= false;
    processus.jetonPresent = false;

    if (processus.pere == processus.pid) {
        processus.jetonPresent = true;
        processus.pere = -1;
    }

    //pour lancer tous les thread d'une maniere sequentielles
    thread site(lanceur);
    //tq le processus n'a pas ete tuéer
    while (!theEnd) {
       auto msg = recevoirMessage();
        cout << "Le processus numero "<<processus.pid << " a recu  ";
        switch (msg.type) {
        case typeDeMessage::REQUETE:
            cout<< " une reqeuete du processus numero " << msg.from << "\n";
            recevoirRequete(msg);
            break;
        case typeDeMessage::JETON:
            cout<< " un jeton du processus numero " << msg.from << "\n";
            processus.jetonPresent = true;
            break;
        default : cout <<"erreur "<<endl;
        }
    }
    MPI_Finalize();
    return 0;
}