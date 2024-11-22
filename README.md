# We are making a project of data structures (teacher request)
<h3> The project is not done yet, we got othe things to do than writing java code</h3>

<p>
This is suposed to be a system where we can input a path with a bunch of .txt files<br>
The system reads the texts and compresses it using huffman algorithm and indexes the compressed texts specific hash algorihtm chosen by the user to set the positions<br>
Then the user can serach for words, indexed in a Trie structure, and the system must tell witch files contains the words, if any.
</p>

<p>After that we have to make a report with an analysis of the perfomance of the code</p>
<p>Now we wrote a version of the main class, called Analisys.java, this is a simplified version of the Main with no user output; instead it gets execution time and memory usage and writes into txt files</p>
<p>The auto.sh file atomates the testing process, so we can set the properties we want and the amount of tests, sit and wait for the script to do the rest while we watch some crap on instagram or anything</p>

# Running the programs:
First if all make sure you have java an python installed<br>
If you have that you can start extracting the text from pdfs with Py Mu Pdf, if you dont have it just run
`pip install pymupdf`
By running
`python(python_version) pyVenv pdftxt.py`
Override (python_version) with your python version of course, or run it your way; maybe you'll need a python virtual environment<br>

you can give a path to a folder with some pdfs that takes the texts from them an produces another folder folder with the texts.

Then you can run
`javac -d theClasses code/Main.java code/FileData.java code/compresses/*.java code/hashes/*.java code/trie/*.java`
to compile the java code, it produces a folder 'theClasses' with all the classes; go the that folder running
`cd theClasses` and then run the code
`java Main`

<br>to compile the analisys just change "Main" to "Analisys" in the above commands

Of course if you are using an IDE for java you dont need any of this :)
except the python code if you dont have a bunch of txts alredy
