/*
 *  BoardTest.java
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
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.*;
import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.*;

public class BoardTest {

    /** public fixtures are used also in other test classes. */
    public Board fixtBoardB;
    public Board fixtBoardBC3;
    public Board fixtBoardBC6;
    public Board fixtBoardC;
    private Board fixtBoardEqlA;
    private Board fixtBoardEqlB;
    public Board fixtBoardMinimaxA;
    public Board fixtBoardMinimaxB;

    /** Fixtures used to test the make move function. */
    private Board fixtBoardMakeMoveA;
    private Board fixtBoardMakeMoveAm;
    private Board fixtBoardMakeMoveB;
    private Board fixtBoardMakeMoveBm;
    private Board fixtBoardMakeMoveC;
    private Board fixtBoardMakeMoveCm;
    private Board fixtBoardMakeMoveD;
    private Board fixtBoardMakeMoveDm;

    /** REPLACED BY THE BoardBuilder CLASS. Has to be removed. */
    /** Static Factory for the board class. */
    static Board boardFromList(List<Integer> il) {
        if (il == null) return null;
        if (il.size() != Square.values().length) throw new IllegalArgumentException();
        Map<Square, SquareState> sm = new EnumMap<Square, SquareState>(Square.class);
        for (int idx=0; idx<Square.values().length; idx++) {
            Integer iss = il.get(idx);
            SquareState ss = null;
            if (iss == null || iss < 0 || iss > 3) throw new IllegalArgumentException();
            else if (iss == 0) ss = SquareState.EMPTY;
            else if (iss == 1) ss = SquareState.BLACK;
            else if (iss == 2) ss = SquareState.WHITE;
            else if (iss == 3) ss = SquareState.OUTER;
            sm.put(Square.getInstance(idx), ss);
        }       
        return Board.valueOf(sm);
    }

    @Before
    public void setUp() {
        fixtBoardB = 
            boardFromList(Arrays.asList(0, 0, 0, 1, 1, 1, 0, 0,
                                        0, 0, 0, 0, 1, 0, 0, 0,
                                        0, 0, 0, 1, 1, 2, 2, 0,
                                        0, 0, 0, 1, 1, 0, 0, 0,
                                        0, 0, 0, 1, 1, 0, 0, 0,
                                        0, 0, 0, 0, 1, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0));

        fixtBoardBC3 = 
            boardFromList(Arrays.asList(0, 0, 0, 1, 1, 1, 0, 0,
                                        0, 0, 0, 0, 1, 0, 0, 0,
                                        0, 0, 2, 2, 2, 2, 2, 0,
                                        0, 0, 0, 1, 1, 0, 0, 0,
                                        0, 0, 0, 1, 1, 0, 0, 0,
                                        0, 0, 0, 0, 1, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0));

        fixtBoardBC6 = 
            boardFromList(Arrays.asList(0, 0, 0, 1, 1, 1, 0, 0,
                                        0, 0, 0, 0, 1, 0, 0, 0,
                                        0, 0, 0, 1, 1, 2, 2, 0,
                                        0, 0, 0, 1, 2, 0, 0, 0,
                                        0, 0, 0, 2, 1, 0, 0, 0,
                                        0, 0, 2, 0, 1, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0));

        fixtBoardC = 
            boardFromList(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0,
                                        2, 1, 1, 1, 0, 0, 2, 0,
                                        0, 2, 0, 2, 1, 2, 0, 0,
                                        0, 2, 2, 1, 2, 0, 0, 0,
                                        0, 0, 1, 1, 0, 2, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0));

        List<Integer> listFixtBoardEql = 
            Arrays.asList(2, 1, 0, 1, 0, 2, 0, 0,
                          1, 1, 1, 1, 1, 1, 1, 2,
                          0, 1, 2, 2, 1, 1, 2, 2,
                          0, 1, 2, 1, 2, 2, 2, 2,
                          0, 1, 2, 1, 2, 2, 2, 2,
                          0, 1, 2, 1, 1, 2, 1, 2,
                          0, 1, 2, 1, 1, 1, 1, 0,
                          2, 2, 2, 2, 2, 2, 1, 2);
        fixtBoardEqlA = boardFromList(listFixtBoardEql);
        fixtBoardEqlB = boardFromList(listFixtBoardEql);

        fixtBoardMinimaxA = 
            boardFromList(Arrays.asList(2, 0, 2, 0, 2, 0, 2, 0,
                                        1, 0, 1, 0, 1, 0, 1, 0,
                                        0, 0, 1, 0, 1, 0, 1, 0,
                                        0, 0, 0, 0, 1, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0));

        fixtBoardMinimaxB = 
            boardFromList(Arrays.asList(2, 1, 2, 0, 0, 0, 0, 0,
                                        1, 0, 1, 0, 0, 0, 0, 0,
                                        0, 0, 1, 0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0));

        fixtBoardMakeMoveA = 
            boardFromList(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0,
                                        0, 2, 2, 2, 2, 2, 0, 0,
                                        0, 2, 1, 1, 1, 2, 0, 0,
                                        0, 2, 1, 0, 1, 2, 0, 0,
                                        0, 2, 1, 1, 1, 2, 0, 0,
                                        0, 2, 2, 2, 2, 2, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0));

        fixtBoardMakeMoveAm = 
            boardFromList(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0,
                                        0, 2, 2, 2, 2, 2, 0, 0,
                                        0, 2, 2, 2, 2, 2, 0, 0,
                                        0, 2, 2, 2, 2, 2, 0, 0,
                                        0, 2, 2, 2, 2, 2, 0, 0,
                                        0, 2, 2, 2, 2, 2, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0));

        fixtBoardMakeMoveB = 
            boardFromList(Arrays.asList(2, 2, 2, 2, 2, 2, 2, 0,
                                        2, 1, 1, 1, 1, 1, 2, 0,
                                        2, 1, 1, 1, 1, 1, 2, 0,
                                        2, 1, 1, 0, 1, 1, 2, 0,
                                        2, 1, 1, 1, 1, 1, 2, 0,
                                        2, 1, 1, 1, 1, 1, 2, 0,
                                        2, 2, 2, 2, 2, 2, 2, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0));

        fixtBoardMakeMoveBm = 
            boardFromList(Arrays.asList(2, 2, 2, 2, 2, 2, 2, 0,
                                        2, 2, 1, 2, 1, 2, 2, 0,
                                        2, 1, 2, 2, 2, 1, 2, 0,
                                        2, 2, 2, 2, 2, 2, 2, 0,
                                        2, 1, 2, 2, 2, 1, 2, 0,
                                        2, 2, 1, 2, 1, 2, 2, 0,
                                        2, 2, 2, 2, 2, 2, 2, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0));

        fixtBoardMakeMoveC = 
            boardFromList(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 2,
                                        1, 1, 1, 1, 1, 1, 1, 2,
                                        1, 1, 1, 1, 1, 1, 1, 2,
                                        1, 1, 1, 0, 1, 1, 1, 2,
                                        1, 1, 1, 1, 1, 1, 1, 2,
                                        1, 1, 1, 1, 1, 1, 1, 2,
                                        1, 1, 1, 1, 1, 1, 1, 2,
                                        2, 2, 2, 2, 2, 2, 2, 2));


        fixtBoardMakeMoveCm = 
            boardFromList(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 2,
                                        1, 1, 1, 1, 1, 1, 1, 2,
                                        1, 1, 1, 1, 1, 1, 1, 2,
                                        1, 1, 1, 2, 2, 2, 2, 2,
                                        1, 1, 1, 2, 2, 1, 1, 2,
                                        1, 1, 1, 2, 1, 2, 1, 2,
                                        1, 1, 1, 2, 1, 1, 2, 2,
                                        2, 2, 2, 2, 2, 2, 2, 2));

        fixtBoardMakeMoveD = 
            boardFromList(Arrays.asList(0, 1, 0, 0, 2, 0, 0, 0,
                                        0, 2, 0, 2, 0, 0, 0, 0,
                                        1, 1, 1, 0, 0, 0, 0, 0,
                                        1, 0, 1, 0, 2, 0, 0, 0,
                                        1, 1, 1, 0, 0, 0, 0, 0,
                                        0, 2, 0, 1, 0, 0, 0, 0,
                                        0, 1, 0, 0, 2, 0, 0, 0,
                                        0, 2, 0, 0, 0, 0, 0, 0));

        fixtBoardMakeMoveDm = 
            boardFromList(Arrays.asList(0, 1, 0, 0, 2, 0, 0, 0,
                                        0, 2, 0, 2, 0, 0, 0, 0,
                                        1, 2, 2, 0, 0, 0, 0, 0,
                                        1, 2, 1, 0, 2, 0, 0, 0,
                                        1, 2, 2, 0, 0, 0, 0, 0,
                                        0, 2, 0, 2, 0, 0, 0, 0,
                                        0, 1, 0, 0, 2, 0, 0, 0,
                                        0, 2, 0, 0, 0, 0, 0, 0));

    }

    @Test
    public void testCountDifference() {
        try {
            BoardFixtures.INITIAL.countDifference(null);
            fail("An exception must be risen.");
        } catch (NullPointerException npe) {
            assertTrue(true);
        }

        assertEquals(0, Board.initialBoard().countDifference(Player.BLACK));
        assertEquals(+10, BoardFixtures.FINAL_B37_W27.countDifference(Player.BLACK));
        assertEquals(-10, BoardFixtures.FINAL_B37_W27.countDifference(Player.WHITE));
        assertEquals(-2, fixtBoardC.countDifference(Player.BLACK));     
    }

    @Test
    public void testCountPieces() {
        try {
            BoardFixtures.INITIAL.countPieces(null);
            fail("A NullPointerException must be risen when the parameter player is null.");
        } catch (NullPointerException npe) {
            assertTrue(true);
        }

        assertThat("Black player has two discs in the initial board configuration.",
                   BoardFixtures.INITIAL.countPieces(SquareState.BLACK), is(2));
        assertThat("White player has two discs in the initial board configuration.",
                   BoardFixtures.INITIAL.countPieces(SquareState.WHITE), is(2));
        assertThat("There are sixty empty squares in the initial board configuration.",
                   BoardFixtures.INITIAL.countPieces(SquareState.EMPTY), is(60));
        assertThat("There are no outer squares in any board configuration.",
                   BoardFixtures.INITIAL.countPieces(SquareState.OUTER), is(0));
    }

    @Test
    public void testEmptyBoard() {
        assertEquals(BoardFixtures.EMPTY, Board.emptyBoard());
        Board empty = Board.emptyBoard();
        for (Square square : Square.values()) {
            assertEquals(SquareState.EMPTY, empty.get(square));
        }
    }

    @Test
    public void testEquals() {
        assertFalse(BoardFixtures.INITIAL.equals(null));
        assertFalse(BoardFixtures.INITIAL.equals(new Object()));
        assertFalse(BoardFixtures.INITIAL.equals(BoardFixtures.FIRST_MOVE_D3));

        assertTrue(BoardFixtures.INITIAL.equals(BoardFixtures.INITIAL));
        assertTrue(BoardFixtures.INITIAL.equals(Board.initialBoard()));
        assertTrue(Board.initialBoard().equals(BoardFixtures.INITIAL));

        assertTrue(fixtBoardEqlA.equals(fixtBoardEqlA));

        assertTrue(fixtBoardEqlA.equals(fixtBoardEqlB));
        assertTrue(fixtBoardEqlB.equals(fixtBoardEqlA));
    }

    @Test
    public void testGet() {
        assertEquals(SquareState.BLACK, fixtBoardC.get(Square.B3));
        assertEquals(SquareState.WHITE, fixtBoardC.get(Square.B4));
        assertEquals(SquareState.OUTER, fixtBoardC.get(null));
        assertEquals(SquareState.EMPTY, fixtBoardC.get(Square.A1));
    }

    @Test
    public void testHasAnyLegalMove() {
        assertTrue(BoardFixtures.INITIAL.hasAnyLegalMove(Player.BLACK));
        assertFalse(BoardFixtures.BLACK_HAS_TO_PASS.hasAnyLegalMove(Player.BLACK));
        assertTrue(BoardFixtures.BLACK_HAS_TO_PASS.hasAnyLegalMove(Player.WHITE));
        assertFalse(BoardFixtures.FINAL_B37_W27.hasAnyLegalMove(Player.WHITE));
        assertFalse(BoardFixtures.FINAL_B37_W27.hasAnyLegalMove(Player.BLACK));
    }

    @Test
    public void testHasAnyPlayerAnyLegalMove() {
        assertFalse(BoardFixtures.EMPTY.hasAnyPlayerAnyLegalMove());
        assertTrue(BoardFixtures.INITIAL.hasAnyPlayerAnyLegalMove());
        assertTrue(BoardFixtures.BLACK_HAS_TO_PASS.hasAnyPlayerAnyLegalMove());
        assertFalse(BoardFixtures.FINAL_B37_W27.hasAnyPlayerAnyLegalMove());
    }

    @Test
    public void testHashCode() {
        assertEquals(BoardFixtures.INITIAL.hashCode(), BoardFixtures.INITIAL.hashCode());
        assertEquals(BoardFixtures.EMPTY.hashCode(), BoardFixtures.EMPTY.hashCode());
        assertEquals(BoardFixtures.BLACK_HAS_TO_PASS.hashCode(), BoardFixtures.BLACK_HAS_TO_PASS.hashCode());
        assertEquals(BoardFixtures.FINAL_B37_W27.hashCode(), BoardFixtures.FINAL_B37_W27.hashCode());
        assertEquals(BoardFixtures.FIRST_MOVE_D3.hashCode(), BoardFixtures.FIRST_MOVE_D3.hashCode());
        assertEquals(fixtBoardB.hashCode(), fixtBoardB.hashCode());
        assertEquals(fixtBoardC.hashCode(), fixtBoardC.hashCode());
        assertEquals(fixtBoardEqlA.hashCode(), fixtBoardEqlA.hashCode());
        assertEquals(fixtBoardEqlB.hashCode(), fixtBoardEqlB.hashCode());

        assertEquals(fixtBoardEqlA.hashCode(), fixtBoardEqlB.hashCode());
        assertEquals(fixtBoardEqlB.hashCode(), fixtBoardEqlA.hashCode());
    }

    @Test
    public void testInitialBoard() {
        assertEquals(BoardFixtures.INITIAL, Board.initialBoard());
        Board initial = Board.initialBoard();
        for (Square square : Square.values()) {
            SquareState actual = initial.get(square);
            SquareState expected;
            switch (square) {
            case D4: expected = SquareState.WHITE; break;
            case E4: expected = SquareState.BLACK; break;
            case D5: expected = SquareState.BLACK; break;
            case E5: expected = SquareState.WHITE; break;
            default: expected = SquareState.EMPTY; break;
            }
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testIsLegal() {

        try {
            BoardFixtures.INITIAL.isLegal(null, Player.BLACK);
            fail("An exception must be risen.");
        } catch (NullPointerException npe) {
            assertTrue(true);
        }

        try {
            BoardFixtures.INITIAL.isLegal(Square.D3, null);
            fail("An exception must be risen.");
        } catch (NullPointerException npe) {
            assertTrue(true);
        }

        /** D4 is already occupied by a disk in the initial board. */
        assertFalse(BoardFixtures.INITIAL.isLegal(Square.D4, Player.BLACK));
        assertFalse(BoardFixtures.INITIAL.isLegal(Square.D4, Player.WHITE));

        /** Corner A1 is not a legal move given the initial board. */
        assertFalse(BoardFixtures.INITIAL.isLegal(Square.A1, Player.BLACK));
        assertFalse(BoardFixtures.INITIAL.isLegal(Square.A1, Player.WHITE));

        /** D3 is one of the four legal moves that the black player has in the opening. */
        assertTrue(BoardFixtures.INITIAL.isLegal(Square.D3, Player.BLACK));

        /** E3 is not among the legal opening moves. */
        assertFalse(BoardFixtures.INITIAL.isLegal(Square.E3, Player.BLACK));

        assertFalse(BoardFixtures.BLACK_HAS_TO_PASS.isLegal(Square.H7, Player.BLACK));
        assertTrue(BoardFixtures.BLACK_HAS_TO_PASS.isLegal(Square.H7, Player.WHITE));

        assertTrue(fixtBoardB.isLegal(Square.C3, Player.WHITE));
        assertTrue(fixtBoardB.isLegal(Square.C6, Player.WHITE));

    }

    @Test
    public void testLegalMoves() {
        try {
            BoardFixtures.INITIAL.legalMoves(null);
            fail("An exception must be risen.");
        } catch (NullPointerException npe) {
            assertTrue(true);
        }

        {
            List<Square> lm = Arrays.asList(Square.D3, Square.C4, Square.F5, Square.E6);
            assertEquals(lm, BoardFixtures.INITIAL.legalMoves(Player.BLACK));
        }

        {
            List<Square> lm = Arrays.asList(Square.C3, Square.E3, Square.C5);
            assertEquals(lm, BoardFixtures.FIRST_MOVE_D3.legalMoves(Player.WHITE));
        }

        {
            List<Square> lm = Arrays.asList(Square.H2, Square.A4, Square.C4, 
                                            Square.G4, Square.A5, Square.F5, 
                                            Square.B6, Square.E6, Square.G7);
            assertEquals(lm, fixtBoardC.legalMoves(Player.BLACK));
        }

        {
            List<Square> lm = new ArrayList<Square>();
            assertEquals(lm, BoardFixtures.BLACK_HAS_TO_PASS.legalMoves(Player.BLACK));
        }

        {
            List<Square> lm = Arrays.asList(Square.A3, Square.C4, Square.G4, Square.E5);
            assertEquals(lm, fixtBoardMinimaxA.legalMoves(Player.WHITE));
        }

        {
            List<Square> lm = Arrays.asList(Square.C3, Square.C6);
            assertEquals(lm, fixtBoardB.legalMoves(Player.WHITE));
        }

        {
            List<Square> lm = Arrays.asList(Square.B2, Square.C2, Square.D2,
                                            Square.F2, Square.G2, Square.C4,
                                            Square.G4);
            assertEquals(lm, fixtBoardBC3.legalMoves(Player.BLACK));
        }

        {
            List<Square> lm = Arrays.asList(Square.H3, Square.C4, Square.F4,
                                            Square.G4, Square.C5, Square.F5,
                                            Square.D6);
            assertEquals(lm, fixtBoardBC6.legalMoves(Player.BLACK));
        }

    }

    /**
     * Tests the mechanics of the makeMove() method.
     */
    @Test
    public void testMakeMove() {

        /** Tests that a null player cannot be passed to makeMove. */
        try {
            BoardFixtures.INITIAL.makeMove(Square.D3, null);
            fail("An exception must be risen.");
        } catch (NullPointerException npe) {
            assertTrue(true);
        }

        /** Tests that a null move cannot be passed to makeMove when a legal one is there. */
        try {
            BoardFixtures.INITIAL.makeMove(null, Player.BLACK);
            fail("An exception must be risen.");
        } catch (NullPointerException npe) {
            assertTrue(true);
        }
        
        /** 
         * Tests that a null move can be a valid parameter when no legal moves are available to the player.
         * Tests also that the return value is the board itself.
         */
        assertEquals(BoardFixtures.BLACK_HAS_TO_PASS, BoardFixtures.BLACK_HAS_TO_PASS.makeMove(null, Player.BLACK));

        /** Tests that an illegal move cannot be passed to makeMove. */
        try {
            BoardFixtures.INITIAL.makeMove(Square.A1, Player.BLACK);
            fail("An exception must be risen.");
        } catch (IllegalArgumentException iae) {
            assertTrue(true);
        }

        /** Move D3 by black sent to the initial board returns the BoardFixtures.FIRST_MOVE_D3. */
        assertTrue(BoardFixtures.FIRST_MOVE_D3.equals(BoardFixtures.INITIAL.makeMove(Square.D3, Player.BLACK)));
        /** Move C3 by white sent to the fixtBoardB board returns the fixtBoardBC3. */
        assertTrue(fixtBoardBC3.equals(fixtBoardB.makeMove(Square.C3, Player.WHITE)));
        /** Move C6 by white sent to the fixtBoardB board returns the fixtBoardBC6. */
        assertTrue(fixtBoardBC6.equals(fixtBoardB.makeMove(Square.C6, Player.WHITE)));

        /** A few basic cases, designed to test the function as much as possible. */
        assertTrue(fixtBoardMakeMoveAm.equals(fixtBoardMakeMoveA.makeMove(Square.D4, Player.WHITE)));
        assertTrue(fixtBoardMakeMoveBm.equals(fixtBoardMakeMoveB.makeMove(Square.D4, Player.WHITE)));
        assertTrue(fixtBoardMakeMoveCm.equals(fixtBoardMakeMoveC.makeMove(Square.D4, Player.WHITE)));
        assertTrue(fixtBoardMakeMoveDm.equals(fixtBoardMakeMoveD.makeMove(Square.B4, Player.WHITE)));

    }

    @Test
    public void testNextToPlay() {
        try {
            BoardFixtures.INITIAL.nextToPlay(null);
            fail("An exception must be risen.");
        } catch (NullPointerException npe) {
            assertTrue(true);
        }

        assertEquals(Player.BLACK, BoardFixtures.INITIAL.nextToPlay(Player.WHITE));
        assertEquals(Player.WHITE, BoardFixtures.INITIAL.nextToPlay(Player.BLACK));
        assertEquals(null, BoardFixtures.FINAL_B37_W27.nextToPlay(Player.WHITE));
        assertEquals(null, BoardFixtures.FINAL_B37_W27.nextToPlay(Player.BLACK));
        assertEquals(Player.WHITE, BoardFixtures.BLACK_HAS_TO_PASS.nextToPlay(Player.WHITE));
        assertEquals(Player.WHITE, BoardFixtures.BLACK_HAS_TO_PASS.nextToPlay(Player.BLACK));
    }

    @Test
    public void testPrintBoard() {
        StringBuilder initialBoard = new StringBuilder();
        initialBoard.append("    a b c d e f g h \n");
        initialBoard.append(" 1  . . . . . . . . \n");
        initialBoard.append(" 2  . . . . . . . . \n");
        initialBoard.append(" 3  . . . . . . . . \n");
        initialBoard.append(" 4  . . . O @ . . . \n");
        initialBoard.append(" 5  . . . @ O . . . \n");
        initialBoard.append(" 6  . . . . . . . . \n");
        initialBoard.append(" 7  . . . . . . . . \n");
        initialBoard.append(" 8  . . . . . . . . \n");
        assertEquals(initialBoard.toString(), BoardFixtures.INITIAL.printBoard());
    }

    @Test
    public void testPrintBoardWithCount() {
        StringBuilder initialBoard = new StringBuilder();
        initialBoard.append("    a b c d e f g h [@=2 0=2 (0)]\n");
        initialBoard.append(" 1  . . . . . . . . \n");
        initialBoard.append(" 2  . . . . . . . . \n");
        initialBoard.append(" 3  . . . . . . . . \n");
        initialBoard.append(" 4  . . . O @ . . . \n");
        initialBoard.append(" 5  . . . @ O . . . \n");
        initialBoard.append(" 6  . . . . . . . . \n");
        initialBoard.append(" 7  . . . . . . . . \n");
        initialBoard.append(" 8  . . . . . . . . \n");
        assertEquals(initialBoard.toString(), BoardFixtures.INITIAL.printBoardWithCount());
    }

    @Test
    public void testPrintCount() {
        assertEquals("[@=2 0=2 (0)]", BoardFixtures.INITIAL.printCount());
        assertEquals("[@=26 0=28 (-2)]", BoardFixtures.BLACK_HAS_TO_PASS.printCount());
    }

    @Test
    public void testValueOf() {

        /**
         * Tests if the valueOf method throws a NullPointerException when
         * the passed map is null.
         */
        try {
            Board.valueOf(null);
            fail("An exception must be risen.");
        } catch (NullPointerException npe) {
            assertTrue(true);
        }

        /**
         * Tests if the valueOf method throws an IllegalArgumentException when
         * the passed map has one or more missing keys.
         */
        {
            Map<Square, SquareState> notCompleteSquareMap = new EnumMap<Square, SquareState>(Square.class);
            notCompleteSquareMap.put(Square.A1, SquareState.EMPTY);
            try {
                Board.valueOf(notCompleteSquareMap);
                fail("An exception must be risen.");
            } catch (IllegalArgumentException iae) {
                assertTrue(true);
            }
        }
 
        /**
         * Tests if the valueOf method throws a NullPointerException when
         * the passed map has a null key.
         */
        {
            Map<Square, SquareState> corruptedSquareHashMap = new HashMap<Square, SquareState>();
            for (Square sq : Square.values()) {
                corruptedSquareHashMap.put(sq, SquareState.EMPTY);
            }
            corruptedSquareHashMap.remove(Square.H8);
            corruptedSquareHashMap.put(null, SquareState.EMPTY);
            try {
                Board corruptedBoard = Board.valueOf(corruptedSquareHashMap);
                fail("An exception must be risen.");
            } catch (NullPointerException npe) {
                assertTrue(true);
            }
        }
 
        /**
         * Tests if the valueOf method returns the supposed Board. It is the
         * standard usage under expected behavior.
         */
        {
            Map<Square, SquareState> squareMap = new EnumMap<Square, SquareState>(Square.class);
            for (Square sq : Square.values()) {
                squareMap.put(sq, fixtBoardC.get(sq));
            }
            Board boardC0 = Board.valueOf(squareMap);
            for (Square sq : Square.values()) {
                assertEquals(fixtBoardC.get(sq), boardC0.get(sq));
            }
        }

        /**
         * Tests if the valueOf method returns the supposed Board. It is the
         * standard usage under expected behavior.
         * In this test the passed map is an HashMap instead of the "standard" EnumMap.
         */
        {
            Map<Square, SquareState> squareHashMap = new HashMap<Square, SquareState>();
            for (Square sq : Square.values()) {
                squareHashMap.put(sq, fixtBoardC.get(sq));
            }
            Board boardC1 = Board.valueOf(squareHashMap);
            for (Square sq : Square.values()) {
                assertEquals(fixtBoardC.get(sq), boardC1.get(sq));
            }
        }
        
    }

    /** 
     * findBracketingPiece is a "private" method in Board class.
     * It is used by only one "client":
     * - the wouldFlip method
     */
    @Test
    public void testFindBracketingPiece() {
        {
            Square move = Square.H7;
            Direction dir = Direction.W;
            Square b1 = move.neighbors().get(dir);
            Square b2 = Square.C7;
            assertEquals(b2, BoardFixtures.BLACK_HAS_TO_PASS.
                         findBracketingPiece(b1, Player.WHITE, dir));
        }

        {
            Square move = Square.H7;
            Direction dir = Direction.NW;
            Square b1 = move.neighbors().get(dir);
            Square b2 = Square.F5;
            assertEquals(b2, BoardFixtures.BLACK_HAS_TO_PASS.
                         findBracketingPiece(b1, Player.WHITE, dir));
        }

        {       
            Square move = Square.H7;
            Direction dir = Direction.SW;
            Square b1 = move.neighbors().get(dir);
            Square b2 = null;
            assertEquals(b2, BoardFixtures.BLACK_HAS_TO_PASS.
                         findBracketingPiece(b1, Player.WHITE, dir));
        }
    }

    /** 
     * wouldFlip is a "private" method in Board class.
     * It is used by only two "clients":
     * - the makeMove method
     * - the isLegal methid
     * both defined in the class itself.
     */
    @Test
    public void testWouldFlip() {
        assertEquals(Square.C7,
                     BoardFixtures.BLACK_HAS_TO_PASS.wouldFlip(Square.H7, Player.WHITE, Direction.W));
        assertEquals(null,
                     BoardFixtures.BLACK_HAS_TO_PASS.wouldFlip(Square.H7, Player.WHITE, Direction.S));
    }

}
