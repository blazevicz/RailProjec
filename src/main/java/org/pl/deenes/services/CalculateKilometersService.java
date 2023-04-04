package org.pl.deenes.services;

import org.pl.deenes.data.Line;

import java.util.LinkedList;

public interface CalculateKilometersService {

    LinkedList<Line> createLinesAndAddToLineList(Double lastKilometer);
}
