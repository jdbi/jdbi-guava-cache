/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jdbi.v3.cache.guava;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.core.statement.ColonPrefixSqlParser;
import org.jdbi.v3.core.statement.SqlStatements;

import static org.jdbi.v3.core.statement.CachingSqlParser.PARSED_SQL_CACHE_SIZE;
import static org.jdbi.v3.core.statement.SqlStatements.SQL_TEMPLATE_CACHE_SIZE;

/**
 * Installing this plugin uses the Guava {@link com.google.common.cache.Cache} cache for SQL statements and the colon prefix parser.
 */
public final class GuavaCachePlugin implements JdbiPlugin {

    @Override
    public void customizeJdbi(Jdbi jdbi) {
        final SqlStatements config = jdbi.getConfig(SqlStatements.class);

        config.setTemplateCache(GuavaCacheBuilder.instance().maxSize(SQL_TEMPLATE_CACHE_SIZE));
        config.setSqlParser(new ColonPrefixSqlParser(GuavaCacheBuilder.instance().maxSize(PARSED_SQL_CACHE_SIZE)));
    }
}
