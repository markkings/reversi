/*
 *  package-info.java
 *
 *  Copyright (c) 2012 Roberto Corradini. All rights reserved.
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
The board package is part of the Reversi Program.
It provides interfaces and implementations that describe the reversi board and its components.
<p>
To do:
<p>

<ul>
  <li>Verify that tests are placed in the right file.</li>
  <li>Prepare tests for the new classes (BoardBuilder, BoardFactoryHolder, ...).</li>
  <li>Complete the hints given by the checkstyle tool (javadocs, and more).</li>
  <li>Develop the precompiuting of the flipping using the FILES prepared in BitBoard.</li>
  <li>Row, Column, Direction, and Square:
      Notes: (1) shift(int delta) in Row and Column classes is used just in the neighbor table calculation. It is a bit ugly.
             (2a) deltaRow and deltaColumn are int, could be better having an enum Shift UP, NEUTRAL, DOWN...?
             (2b) shift(int delta) method in Row and Column would be transformed accordingly ....</li>
  <li>Prepare a "<i>Literate Paper</i>" that describes the software architecture of the board package.</li>
  <li>Develop JUnit performance testing and reports for different board implementations.
      See: <a href="http://databene.org/contiperf.html" target="_blank">ContiPerf</a></li>
      See: <a href="http://perf4j.codehaus.org/" target="_blank">Perf4J</a></li>
</ul>

<p>


@author Roberto Corradini
@version 1.1.0
*/

package rcrr.reversi.board;
