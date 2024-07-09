package de.taimos.dvalin.jms.exceptions;

/*
 * #%L
 * Dvalin interconnect core library
 * %%
 * Copyright (C) 2016 Taimos GmbH
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

/**
 * Exception for all problems concerning the Interconnect infrastructure.
 *
 * @author thoeger
 */
public class InfrastructureException extends Exception {

    /**
     * version UID for serialization
     */
    private static final long serialVersionUID = 1L;


    /**
     * Default constructor.
     */
    public InfrastructureException() {
        this("A problem with the Interconnect infrastructure occured", null);
    }

    /**
     * Constructor.
     *
     * @param message a description of the problem
     */
    public InfrastructureException(String message) {
        this(message, null);
    }

    /**
     * Constructor.
     *
     * @param message a description of the problem
     * @param cause   the cause of the problem
     */
    public InfrastructureException(String message, Throwable cause) {
        super(message, cause);
    }

}
