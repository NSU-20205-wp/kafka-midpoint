# Short guide for sending messages to kafka

### example 1:
For <b>kafka-magic</b>: if you want to use such format, you <b>should</b> select "Recognise Message Context"

### Create

    {
        "Message": {
            "requestId": 20,
            "operation": "create",
            "what": "user", 
            "params": {
                "name": "user_test"
            }
        }
    }

"what" can be user, role or resource

### Modify

    {
        "Message": {
            "requestId": 20,
            "operation": "modify",
            "what": "user",
            "params": {
                "name": "user_test",
                "modType": "add",
                "fieldName": "fullName",
                "value": "Ivan"
            }
        }
    }

"what" can be user, role or resource

### Search

    {
        "Message": {
            "requestId": 20,
            "operation": "search",
            "what": "user",
            "params": {
                "fieldName": "name",
                "value": "user_test"
            }
        }
    }

"what" can be user, role, resource or connector

### Delete

    {
        "Message": {
            "requestId": 20,
            "operation": "delete",
            "what": "user",
            "params": {
                "name": "user_test"
            }
        }
    }

"what" can be user, role or resource
 