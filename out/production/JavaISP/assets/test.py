import sys
type = sys.argv[1]
mypath = "assets/"+type+"s"
from os import listdir
from os.path import isfile, join
onlyfiles = [f for f in listdir(mypath) if isfile(join(mypath, f))]
for f in onlyfiles:
    print("textures.put(\""+type.capitalize()+":"+f.split('.')[0]+"\", new Image(\""+mypath+"/"+f+"\"));")