# Short guide for sending messages to kafka

### example 1:
For kafka-magic: if you want to use such format, you should select "Recognise Message Context"

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