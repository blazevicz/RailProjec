package org.pl.deenes.services.interfaces;

import org.pl.deenes.model.Line;

import java.util.LinkedList;

public interface CalculateKilometersService {

    LinkedList<Line> createLinesAndAddToLineList(Double lastKilometer);
}
