Simple Spring statemachine using PostgreDB for state persistence.
To start statemachine, use the get method "/start-state-machine", pre-populate the db with tasks.
The app retrieves all incomplete tasks and starts them from the action on which it ended.