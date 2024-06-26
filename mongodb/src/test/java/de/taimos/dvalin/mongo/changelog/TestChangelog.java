package de.taimos.dvalin.mongo.changelog;

/*
 * #%L
 * Spring DAO Mongo
 * %%
 * Copyright (C) 2013 - 2015 Taimos GmbH
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import de.taimos.dvalin.mongo.ABaseTest;
import de.taimos.dvalin.mongo.ChangelogUtil;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;

@ChangeUnit(order = "001", id = "index1", author = "thoeger", transactional = false)
public class TestChangelog {

    @Execution
    public void index1() {
        ChangelogUtil.addIndex(ABaseTest.mongo.getDatabase(ABaseTest.dbName).getCollection("TestObject"), "name", true, true);
    }

    @RollbackExecution
    public void index1Rollback(){}
}
