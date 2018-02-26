/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.comparators;

import cl.usm.geosansano.entity.Sede;
import java.util.Comparator;

/**
 *
 * @author Alex
 */
public class SedeNameComparator implements Comparator<Sede> {

    @Override
    public int compare(Sede p1, Sede p2) {
        return p1.getSedNomSede().compareTo(p2.getSedNomSede());
    }

}
