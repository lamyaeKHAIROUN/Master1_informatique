const int MSG_SIZE = 2;

struct Processus{

    int size;
    int pid;
    int pere;
    int suivant;
    bool estDemandeur;
    bool jetonPresent;

}processus;

namespace typeDeMessage{

    const int REQUETE=0;
    const int JETON=1;
}

 struct Message {
    int type;
    int from;
};
