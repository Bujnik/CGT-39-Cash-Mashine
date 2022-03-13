package main.command;

import main.exception.InterruptedOperationException;

interface Command {
    void execute() throws InterruptedOperationException;
}
