An implementation of an unidirectional weighted graph.
***Information about NodeInfo class :***
•`key` is Integer type information that is stored in the node.
• `info` is String type information that is stored in the node.
• `tag` is double type information that is stored in the node.
• `getKey()` method return the key from specified node.
• `getInfo()` method return the info from specified node.
•	`getTag()`method return the tag from specified node.
• `setTag()` method allows you to set the tag of a specified node.
• `setInfo()` method allows you to set the info of a specified node.
• `equals(Object obj)`method return true  either the objects are equal to each other or return false if they are not equal.
• `toString()` method will print to the screen all the information of a specified node.
 ***Information about WGraph_DS class :***
 • `nodes` representing all the vertices of the Graph using HashMap data structure.
 • `graph` representing all the neighbors and the weight between a specified node and his neighbors using HashMap data structure.
 • `MC` representing all the changes that were commit on the Graph.
 • `edgeSizeOfG` representing the number of the edges on the Graph.
 • `nodeSizeOfG` representing the number of the nodes on the Graph.
 • `getNode()` method gets a key as argument and return the specified node with this key.
 • `hasEdge()` method gets two arguments and return true or false if their is an edge between them.
 • `getEdge()` method gets two arguments and return the weight between those two nodes with their specified keys that were given as arguments.
 • `addNode()` method gets a key as argument and adds this node with the specified key to the Weighted Graph.
 •`connect()` method gets two keys as arguments and connects the two nodes with the given keys as arguments.
 • `getV()`method return all the vertices of a specified Weighted Graph.
 • `getV()`method gets a key as argument and return all his neighbors.
 • `removeNode()` method gets a key as argument and removes the specified node with this key.
 • `removeEdge()` method gets two keys as arguments and removes the edge between the nodes with the specified keys that were given as arguments.
 • `nodeSize()` method return the number of the nodes in the Weighted Graph.
 • `edgeSize()` method return the number of the edges in the Weighted Graph.
 • `getMC()` method return the number of changes that were done on the Weighted Graph.
 • `equals()` method gets an object as argument and return true or false if the object is equal to the specified object that we are trying to understand if their is an equation between them.
  ***Information about WGraph_Algo class :***
  • `g` is the graph that we are working on.
  • `init()` method initializes the graph that this class will work on
  • `getGraph()` method return the graph that we are working on this class.
  • `copy()` method makes a deep copy of a specified Weighted Graph.
  • `isConnected()` method return true or false if the specified Weighted Graph is connected ( meaning of this connection is that their is a path from every node to every other node that we wish to get  ) .Will return true if this Weighted Graph is connected , or will return false if this Weighted Graph is not connected.
  • `shortestPathDist()` method gets two keys as arguments , the first is the source and the second is the destination . 
  This method use the dijkstra's algorithm , which will return the shortest path from the source node to the destination node by counting the minimum weight sum between all the possible paths to get from the source node to the destination node. This method will return the minimum weight path between those two keys . 
  If there is no such path to get from source node to destination node , the method will return -1;
  • `compare()` method gets two keys as arguments and compares their tags . There are 3 cases , if one is larger than an other , if one is smaller than an other and if they are equal . 
  •`shortestPath()` method gets two keys as arguments the first is the source and the second is the destination .
 This method use the dijkstra's algorithm which will return the shortest path from the source node to the destination node by counting the minimum weight sum between all the possible paths to get from the source node to the destination node.
 This method will return a list of nodes that represent the shortest path between the source node to the destination node.
 If there is no such path , the method will return an empty list.
 • `save()` method get a String as argument which represents the name of the specified file that we wish to save on our computer . This method saves the object on our computer as a file. In case it saved successfully it will return true, otherwise return false. 
 • `load()` method gets  String as argument which represents the name of the file we wish to load from our computer , this method will save the specified file and initialize the graph with the specified information that is stored on the file on our computer. In case it loaded successfully it will return true, otherwise return false. 
  
****Links related****
•  ***Dijkstra's algorithm :***  https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
