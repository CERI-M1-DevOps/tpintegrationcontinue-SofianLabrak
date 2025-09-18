package liste;

public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * Méthode pour obtenir la taille de la liste
     * @return Taille de la liste
     */
    public long getSize() {
        return size;
    }

    /**
     * Méthode pour ajouter un élément en tête de liste
     * @param element élément à ajouter
     * @return void
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Méthode pour modifier le premier élément de la liste égal à un certain élément
     * @param element élément à rechercher
     * @param nouvelleValeur nouvelle valeur à affecter
     * @return void
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
     * Méthode pour modifier tous les éléments de la liste égaux à un certain élément
     * @param element élément à rechercher
     * @param nouvelleValeur description du deuxième paramètre
     * @return void
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /**
     * Méthode pour obtenir une représentation textuelle de la liste
     * @return String représentant la liste
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }


    /**
     * Méthode pour supprimer le premier élément de la liste égal à un certain élément
     * @param element élément à rechercher
     * @return void
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Méthode pour supprimer tous les éléments de la liste égaux à un certain élément
     * @param element élément à rechercher
     * @return void
     */
    public void supprimeTous(int element) {
        tete = supprimeTousRecurs(element, tete);
    }

    /**
     * Méthode récursive pour supprimer tous les éléments de la liste égaux à un certain élément
     * @param tete le noeud courant dans la récursion
     * @param element élément à rechercher
     * @return Noeud la nouvelle tête de liste après suppression
     */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    /**
     * Méthode pour obtenir l'avant-dernier noeud de la liste
     * @return Noeud avant-dernier noeud de la liste, ou null si la liste a moins de deux éléments
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Méthode pour inverser la liste
     * @return void
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Méthode pour obtenir le noeud précédent d'un noeud donné
     * @param r le noeud dont on veut trouver le précédent
     * @return Noeud précédent du noeud donné
     */
    public Noeud getPrecedent(Noeud r) {
        // la liste n'est pas vide puisqu'on transmet un Node de la liste et le Node existe obligatoirement
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
     * Méthode pour échanger deux noeuds dans la liste
     * @param r1 description du premier paramètre
     * @param r2 description du deuxième paramètre
     * @return void
     */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1, precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        } else {
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }

}