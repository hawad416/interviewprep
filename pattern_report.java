/*This is a running log of new techniques I learn, any patterns, mechanisms, and algorithms that can be applied

- lamba functions, especially going through map entries. note that you can't access your local variables which fall outside of the lambda without declaring final or effectively final. The restriction to effectively final variables prohibits access to dynamically-changing local variables, whose capture would likely introduce concurrency problems. Thus, to lower risk of bugs, they decided to ensure captured variables are never mutated.

lambda notation:  */

        frequencyMap.keySet().forEach(key -> {
            kQ.add(key);
        });

/*
- priority queue can be passed in a very elegant comparator that can make use of lambda functions, or you could pass in something else. The default priorityqueue is implemented as a MIN HEAP, so passing in comparator is necessary to transform it to the max heap and manipulate priority
        -    // reverse a and b in the compare since maxHeap */
        PriorityQueue<Integer> kQ = new PriorityQueue<>((a,b) -> Integer.compare(frequencyMap.get(b), frequencyMap.get(a)));
        - PriorityQueue<Integer> kQ = new PriorityQueue<>(Collections.reverseOrder()) --> 
    
                
                /*
- accessing all entries of a map: can either use lambda(if you don't need to use local vars) or use Map.Entry notation or for each loop: 
*/
        for(int key : frequencyMap.keySet()){
            kQ.add(key);
            
            if(kQ.size() > k) kQ.poll();      
        } 

                          //  or: 
        for(Map.Entry<Integer, Integer> entry){

        }

/*

- regex patterns are extremely useful to know. especially in cases where you need to replace or remove all invalid characters in a string
        - replacing all spaces: */ string.replaceAll("\\s", "")
        /*- replacing all non alphanumeric characters: */string.replaceAll("[^A-Za-z0-9]", "")

                /*
- convert string to a char array, then perform manipulations, then convert back to be more efficient and give yourself an easier time.
    - */ string.toCharArray()

- sliding window can kind of encompass the two pointer technique 
