/*
 *  AlphaBeta.java
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

package rcrr.reversi;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Minimax provides some static methods that return {@code Strategy}
 * objects. These searcher methods differ for the algorithm implementation.
 * <p>
 * The Minimax family of algorithms is described by the wikipedia
 * page: <a href="http://en.wikipedia.org/wiki/Minimax">Minimax</a>.
 * <p>
 * <ul>
 *   <li>{@code minimaxSearcher}</li>
 *   <li>{@code alphabetaSearcher}</li>
 * </ul>
 * Javadocs, Unit tests, and semplification are under construction.
 * <p>
 * Must be transformed to be immutable and not instantiable.
 */
public final class AlphaBeta extends AbstractDecisionRule {

    private AlphaBeta() {};

    public static AlphaBeta getInstance() {
	return new AlphaBeta();
    }

    /**
     * The alpha-beta function.
     * <p>
     * Polish as per the minimax method, and write a complete javadoc.
     */
    public SearchNode search(final Player player, final Board board, final int achievable, final int cutoff, final int ply, final EvalFunction ef) {
	SearchNode node;
	Player opponent = player.opponent();
	if (ply == 0) {
	    node = new SearchNode(null, ef.eval(GamePosition.valueOf(board, player)));
	} else {
	    List<Square> moves = board.legalMoves(player);
	    if (moves.isEmpty()) {
		if (board.hasAnyLegalMove(opponent)) {
		    node = search(opponent, board, - cutoff, - achievable, ply - 1, ef).negated();
		} else {
		    node = new SearchNode(null, finalValue(board, player));
		}
	    } else {
		node = new SearchNode(moves.get(0), achievable);
		outer: for (Square move : moves) {
		    Board board2 = board.makeMove(move, player);
		    int val = search(opponent, board2, - cutoff, - node.value(), ply - 1, ef).negated().value();
		    if (val > node.value()) {
			node = new SearchNode(move, val);
		    }
		    if (node.value() >= cutoff) break outer;
		}
	    }
	}
	return node;
    }

}