from heapdict import heapdict

def prim(graph, start):
    keys = heapdict()
    for node in graph.keys():
        keys[node] = float('inf')
        pi[node] = None
    keys[start] = 0

    while keys:
        current_node, current_key = keys.popitem()
        for adjacent, weight in mygraph[current_node].items():
            if adjacent in keys and weight < keys[adjacent]:
                keys[adjacent] = weight

mygraph = {
    'A': {'B': 7, 'D': 5},
    'B': {'A': 7, 'D': 9, 'C': 8, 'E': 7},
    'C': {'B': 8, 'E': 5},
    'D': {'A': 5, 'B': 9, 'E': 7, 'F': 6},
    'E': {'B': 7, 'C': 5, 'D': 7, 'F': 8, 'G': 9},
    'F': {'D': 6, 'E': 8, 'G': 11},
    'G': {'E': 9, 'F': 11}    
}

prim(mygraph,'A')