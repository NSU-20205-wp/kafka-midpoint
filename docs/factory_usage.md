# Factory description procedure

1) add to factory.conf string of format
    
        <factory_name> <path_to_factory>
    path should be relative to the directory of the <b>factory.conf</b> file. <path_to_factory> must have extension .fact
2) in .fact file you should specify factory params
   1. first line -- the fully qualified name of the class that specifies how to create the object
   2. second line -- the fully qualified name of the base class (the class from which all over will be inherited)
   3. all other lines have format
                
            <class_name> <full_qualified_class_name>
      this class must be cast to the base class, otherwise the line will be ignored
   4. <b>NOTE</b> that error in 1 and / or in 2 lines is fatal, so factory won't be created
3) To instantiate a new object, you need to do this:

        AbstractFactory.instance().getFactory(<factory_name>).createProduct(<class_name>, params)

    params has type String[] and could be null