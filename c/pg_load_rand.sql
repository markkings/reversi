
-- PostgreSQL script part of the reversi program.
-- This script deletes and reloads the log generated by endgame_solver (exact_solver) when run with GAME_TREE_DEBUG option turned on.

-- Start psql by running: psql -U reversi -w -d reversi -h localhost
-- Load the file by running the command: \i pg_load_rand.sql
-- Or run by a shell prompt: time psql -U reversi -w -d reversi -h localhost -f pg_load_rand.sql



-- Original table generated by exact_solver.c
DROP TABLE IF EXISTS rand_log;

-- CALL_ID;HASH;PARENT_HASH;GAME_POSITION;EMPTY_COUNT;CALL_LEVEL;IS_LEF;LEGAL_MOVE_COUNT;LEGAL_MOVE_COUNT_ADJUSTED;MOVE_LIST

CREATE TABLE rand_log (call_id                   INTEGER,
                       hash                      VARCHAR(16),
                       parent_hash               VARCHAR(16),
                       game_position             VARCHAR(65),
                       empty_count               INTEGER,
                       call_level                INTEGER,
                       is_leaf                   BOOLEAN,
                       legal_move_count          INTEGER,
                       legal_move_count_adjusted INTEGER,
                       move_list                 VARCHAR(78),
                       PRIMARY KEY(call_id));

\COPY rand_log FROM '/home/rcrr/base/prj/reversi/c/rand_log.csv' WITH (FORMAT CSV, DELIMITER ';', HEADER true);

VACUUM (FULL, ANALYZE, VERBOSE) rand_log;

CREATE INDEX rand_log_hash ON rand_log (hash);

--
SELECT
  rand_log.empty_count AS empty_square_count,
  count(rand_log.empty_count) AS sample_size,
  round(avg(rand_log.legal_move_count_adjusted), 4) AS average_mobility, 
  min(rand_log.legal_move_count_adjusted) AS min_mobility,
  max(rand_log.legal_move_count_adjusted) AS max_mobility,
  round(stddev_pop(rand_log.legal_move_count_adjusted), 4) AS standard_deviation
FROM 
  rand_log
GROUP BY
  rand_log.empty_count
ORDER BY
  rand_log.empty_count DESC;

