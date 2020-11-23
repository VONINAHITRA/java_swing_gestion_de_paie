/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_paie;

import com.forme.accueil_principale;
import com.forme.authentificaiton;
import com.forme.authentification_add;
import com.forme.declaration;
import com.forme.perso_entrep_contrat_avance_forme;
import com.forme.salaire_globale;

/**
 *
 * @author VONIANAHITRA
 */
public class Gestion_paie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //TODO code application logic here
//      perso_entrep_contrat_avance_forme auth = new perso_entrep_contrat_avance_forme();
      // salaire_globale auth = new salaire_globale();
       //declaration auth = new declaration();
 authentificaiton auth = new authentificaiton();
//        accueil_principale  auth = new accueil_principale();
        auth.setVisible(true);
    }
    
}
