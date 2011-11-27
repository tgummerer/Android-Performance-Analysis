#include <cstddef>
#include <stdio.h>
#include "connection.h"

Connection::Connection()
{
    sqlite3_open(DATABASE_LOCATION, &db);
}

Connection::~Connection()
{
	sqlite3_close(db);
}

void Connection::exec(const char * query)
{
    printf("%i\n", sqlite3_exec(db, query, NULL, NULL, NULL));
    fflush(stdout);
}

sqlite3_stmt * Connection::prepare(const char * query)
{
	sqlite3_stmt * pstmt;
	sqlite3_prepare_v2(db, query, -1, &pstmt, NULL);
	return pstmt;
}

