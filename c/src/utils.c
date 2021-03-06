/**
 * @file
 *
 * @brief Utils module implementation.
 *
 * @par utils.c
 * <tt>
 * This file is part of the reversi program
 * http://github.com/rcrr/reversi
 * </tt>
 * @author Roberto Corradini mailto:rob_corradini@yahoo.it
 * @copyright 2014 Roberto Corradini. All rights reserved.
 *
 * @par License
 * <tt>
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 3, or (at your option) any
 * later version.
 * \n
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * \n
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA
 * or visit the site <http://www.gnu.org/licenses/>.
 * </tt>
 */

#include <stdlib.h>

#include "utils.h"



/**
 * @brief Returns a random integer selected in the window between
 * the `low` and `high` function parameters, having the two boundaries
 * in the valid range for the returned value.
 *
 * @param [in] low  the lower bound
 * @param [in] high the upper bound
 * @return          a random value in the given range
 */
int
random_number (int low, int high)
{
  return low + (double) rand() * (high - low + 1) / RAND_MAX;
}
