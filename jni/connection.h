#ifndef __CONNECTION_H__
#define __CONNECTION_H__

#include "sqlite3.h"

#define DATABASE_LOCATION "/data/data/com.tgummerer/databases/PerformanceAnalysis"

class Connection
{
	private:
		sqlite3 *db;

	public:
		Connection();
		~Connection();
		void exec(const char * query);
		sqlite3_stmt * prepare(const char * query);

};

#endif
