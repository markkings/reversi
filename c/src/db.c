/**
 * @file
 *
 * @brief Data Base utilities and programs.
 * @details This executable read and write game position db.
 *
 * @par db.c
 * <tt>
 * This file is part of the reversi program
 * http://github.com/rcrr/reversi
 * </tt>
 * @author Roberto Corradini mailto:rob_corradini@yahoo.it
 * @copyright 2013 Roberto Corradini. All rights reserved.
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

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "board.h"

#define BUFFER_SIZE 64;

typedef struct {
  char   **lines;
  int      line_counter;
  size_t   allocated_size;
} LineList;

char *get_line(FILE *f);
void  load_line_list(FILE *f, LineList *llp);

/**
 * Data Base main entry.
 * 
 * Has to be completly developed.
 */
int main(int argc, char *argv[])
{
  LineList *llp;
  FILE *fp;

  llp = malloc(sizeof(LineList *));

  if (argc == 1) {
    load_line_list(stdin, llp);
  } else {
    while (--argc > 0)
      if ((fp = fopen(*++argv, "r")) == NULL) {
        printf("db: can't open %s\n", *argv);
        return 1;
      } else {
        load_line_list(fp, llp);
        fclose(fp);
      }
  }

  /* Use the db. */
  for (int i = 0; i < llp->line_counter; i++) {
    printf("line[%d]: %s", i, llp->lines[i]);
  }

  /* Release the memory allocated. */
  // to be done ....

  return 0;
}

void load_line_list(FILE *fp, LineList *llp)
{
  while (!feof(fp)) {
    if (llp->line_counter + 1 - (int) llp->allocated_size > 0) {
      llp->allocated_size += BUFFER_SIZE;
      llp->lines = realloc(llp->lines, llp->allocated_size * sizeof(char **));
    }
    llp->lines[llp->line_counter++] = get_line(fp);;
  }
}

char *get_line(FILE *f)
{
  size_t  size;
  char   *buf;
  int     char_counter;
  int     c;

  size         = 0;
  buf          = NULL;
  char_counter = 0;

  for (;;) {
    if (char_counter + 2 - (int) size > 0) { // +2 takes into account the next c that will be read and the appended \0.
      size += BUFFER_SIZE;
      buf = realloc(buf, size);
    }
    if (((c = fgetc(f)) != EOF)) {
      buf[char_counter++] = c;
    }
    if (c == '\n' || c == EOF) {
      buf[char_counter++] = '\0';
      return buf;
    }
  }
}
