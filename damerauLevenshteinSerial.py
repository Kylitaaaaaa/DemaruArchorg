''' DAMERAU-LEVENSHTEIN EDIT DISTANCE (Serial)
    Author: Darlene Marpa, Kyle-Althea Santos
'''

# INPUT #
sourceString = input("Enter source sequence: ")
comparingString = input("Enter comparing sequence: ")

# Creates a rows x cols multidimential array with all elements initialized to 0 #
class multidimen_array(list):
    def __init__(self, rows, cols=-1, blank=None):

        if cols == -1:
            cols = rows
        for x in range(0, rows + 1):
            self.append(list())
            for y in range(0, cols + 1):
                self[x].append(blank)

        self.rows = rows
        self.cols = cols

# (['a', 'b', 'c'], ['c', 'd']) -> {'a':0, 'b':1, 'c':2, 'd':3} #
def getAlphabet(words):

    alphabet = {}
    i = 0
    for wordList in words:
        for word in wordList:
            if word not in alphabet:
                alphabet[word] = i
                i = i + 1
    return alphabet

# DAMERAU-LEVENSHTEIN FUNCTION #
def damerauLevenshteinDistance(a, b):
#   a = source sequence; b = comparing sequence #

    alphabet = getAlphabet((a, b))                      # assigns identifier for each unique alphabet

    maxDist = len(a) + len(b) + 1                       # computes for maximum possible distance
    h = multidimen_array(len(a) + 1, len(b) + 1, 0)     # assures all array are in same length (Sigma parameter)

    for i in range(0, len(a) + 1):
        h[i + 1][1] = i
        h[i + 1][0] = maxDist
    for j in range(0, len(b) + 1):
        h[1][j + 1] = j
        h[0][j + 1] = maxDist

    da = [0 for x in range(0, len(alphabet))]
    for i in range(1, len(a) + 1):
        db = 0
        for j in range(1, len(b) + 1):
            i1 = da[alphabet[b[j - 1]]]
            j1 = db
            cost = 1
            if (a[i - 1] == b[j - 1]):
                print ("MATCH FOUND!\n")
                cost = 0
                db = j

                substitutionScore   = h[i][j] + cost                                # substitution
                print ("Substitution: ", substitutionScore, '\n')
                insertionScore      = h[i + 1][j] + 1                               # insertion
                print ("Insertion: ", insertionScore, '\n')
                deletionScore       = h[i][j + 1] + 1                               # deletion
                print ("Deletion: ", deletionScore, '\n')
                transpositionScore  = h[i1][j1] + (i - i1 - 1) + 1 + (j - j1 - 1)   # transposition
                print ("Transposition: ", transpositionScore, '\n')

                print ('\n')
                h[i + 1][j + 1] = min(
                    substitutionScore,
                    insertionScore,
                    deletionScore,
                    transpositionScore
                )
                print ("MINIMUM: ", h[i + 1][j + 1])

        da[alphabet[a[i - 1]]] = i
    return h[len(a) + 1][len(b) + 1]
