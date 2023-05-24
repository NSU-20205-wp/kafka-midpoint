# Short guide for sending messages to kafka

### example 1:
For <b>kafka-magic</b>: if you want to use such format, you <b>should</b> select "Recognise Message Context"

### Create

    {
        "Message": {
            "requestId": 20,
            "operation": "create",
            "what": "user", // also may be role or resource
            "params": {
                "name": "user_test"
            }
        }
    }

### Delete

    {
        "Message": {
            "requestId": 20,
            "operation": "delete",
            "what": "user", // also may be role or resource
            "params": {
                "name": "user_test"
            }
        }
    }