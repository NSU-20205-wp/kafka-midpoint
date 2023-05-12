# Short guide for sending messages to kafka

### example 1:
if you want to use such format, you should select "Recognise Message Context"

    {
        "Message": {
            "operation": "create",
            "what": "user",
            "params": {
                "name": "user_test"
            }
        }
    }