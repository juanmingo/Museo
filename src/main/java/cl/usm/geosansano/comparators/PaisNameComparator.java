/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.comparators;

import cl.usm.geosansano.entity.Pais;
import java.util.Comparator;

/**
 *
 * @author Alex
 */
public class PaisNameComparator implements Comparator<Pais> {

    @Override
    public int compare(Pais p1, Pais p2) {
        return p1.getNomPais().compareTo(p2.getNomPais());
    }

}
