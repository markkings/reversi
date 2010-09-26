/*
 *  package-info.java
 *
 *  Copyright (c) 2010 Roberto Corradini. All rights reserved.
 *
 *  This file is part of the reversi program
 *  http://github.com/rcrr/reversi
 *
 *  This program is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License as published by the
 *  Free Software Foundation; either version 3, or (at your option) any
 *  later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA
 *  or visit the site <http://www.gnu.org/licenses/>.
 */

/**
The Reversi Program.
Main algorithm and structural classes.
<p>
To do:
<p>
<ul>
  <li>Javadoc has to be completed.</li>
  <li>Reversi class has no tests. Do it.</li>
  <li>Board and Player should be grouped into a compound object.
      It is not GameState, because the Clock is too volatile.</li>
  <li>The end of time is not handled correctly.</li>
  <li>The game should start an "independent" Clock using a timer thread.
      The GameState Clock should be just a transcription value.</li>
  <li>Move has to be a full object having: "put disk", "pass", and "resign".</li>
  <li>Minimax: searcher should be a single method receiving a minimax object ....</li>
  <li>Board + Player should be enclosed into a compound GameNode Object.
      Node should become SearchNode.</li>
  <li>Minimax: The final value should be calculated by the eval function.</li>
  <li>Clock: move the two long fields into an EnumMap having the Players as keys.</li>
  <li>Clock: parameters boundaries are not tested.</li>
  <li>CountDifference: ok</li>
  <li>Game: write junit tests.</li>
  <li>Game: review javadocs.</li>
  <li>GameOverException: delete it.</li>
  <li>GameState: review test completeness.</li>
  <li>HumanStrategy: review input and output streams usage. Review the prompt management.</li>
  <li>MaximizeDifference: write tests and javadocs.</li>
  <li>MinimaxSearcherCountDifference: write javadocs and tests.</li>
  <li>Reversi: Strategies should receive a configuration structure (XML or properties).</li>
  <li>Brainstorming on a strategy builder class.</li>
  <li>ModifiedWeightedSquares: javadocs and tests.</li>
  <li>Player: ok</li>
  <li>RandomStrategy: javadocs and tests.</li>
  <li>SquareState: ok</li>
  <li>WeightedSquares: javadocs and tests.</li>
  <li>....</li>
</ul>

<p>
Java source files:
<p>
<ul>
  <li>AlphabetaSearcherCountDifference</li>
  <li>AlphabetaSearcherModifiedWeightedSquares</li>
  <li>AlphabetaSearcherWeightedSquares</li>
  <li>Board: ok. Javadocs complete. Tests complete.</li>
  <li>Clock</li>
  <li>Column: ok. Javadocs complete. Tests complete. Notes: see Row.</li>
  <li>CountDifference: ok. Javadocs complete. Tests complete.</li>
  <li>Direction: ok. Javadocs complete. Tests complete.
      Notes: (1) deltaRow and deltaColumn are int, could be better having an enum Shift UP, NEUTRAL, DOWN...?
             (2) shift(int delta) method in Row and Column would be transformed accordingly ....</li>
  <li>EvalFunction: ok. Javadocs complete.</li>
  <li>Game</li>
  <li>GameOverException</li>
  <li>GameState</li>
  <li>HumanStrategy</li>
  <li>MaximizeDifference</li>
  <li>Minimax</li>
  <li>MinimaxSearcherCountDifference</li>
  <li>ModifiedWeightedSquares: ok. Javadocs complete. Tests complete.</li>
  <li>package-info</li>
  <li>Player</li>
  <li>RandomStrategy</li>
  <li>Reversi</li>
  <li>Row: ok. Javadocs complete. Tests complete.
      Notes: (1) getInstance(int index) is redundant.
             (2) shift(int delta) is used just in the neighbor table calculation. It is a bit ugly.</li>
  <li>Square: ok. Javadocs complete. Tests complete.</li>
  <li>SquareState</li>
  <li>Strategy: ok. Javadocs complete.</li>
  <li>WeightedSquares: ok. Javadocs complete. Tests complete.</li>
</ul>

<p>
JUnit source files:
<p>
<ul>
  <li>BoardTest: tests complete.</li>
  <li>ClockTest</li>
  <li>ColumnTest</li>
  <li>CountDifferenceTest: tests complete.</li>
  <li>DirectionTest</li>
  <li>GameStateTest</li>
  <li>GameTest</li>
  <li>MinimaxTest</li>
  <li>ModifiedWeightedSquaresTest: tests complete.</li>
  <li>PlayerTest</li>
  <li>ReversiTest</li>
  <li>RowTest</li>
  <li>SquareStateTest</li>
  <li>SquareTest</li>
  <li>WeightedSquaresTest: tests complete.</li>
</ul>

@author Roberto Corradini
@version 0.1
*/

package rcrr.reversi;
