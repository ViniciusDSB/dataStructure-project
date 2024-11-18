# We are making a project of data structures (teacher request)
<h3> The project is not done yet, we got othe things to do than writing java code</h3>

<p>
This is suposed to be a system where we can input a path with a bunch of .txt files<br>
The system reads the texts and compresses it using a specific hash algorihtm chosen by the user<br>
Then the user can serach for words and the system must tell witch files contains the words, if any.
</p>

<p>After that we have to make a report with an analysis of the perfomance of the code</p>

# Running the programs:
First if all make sure you have java an python installed<br>
If you have that you can start extracting the text from pdfs with Py Mu Pdf, if you dont have it just run
`pip install pymupdf`
By running
`python(python_version) pyVenv pdftxt.py`
Override (python_version) with your python version of course, or run it your way; maybe you'll need a python virtual environment<br>

you can give a path to a folder with some pdfs that takes the texts from them an produces another folder folder with the texts.

Then you can run
`javac -d theClasses code/Main.java code/compresses/*.java code/hashes/*.java`
to compile the java code, it produces a folder 'theClasses' with all the classes; go the that folder running
`cd theClasses` and then run the code
`java Main`

Of course if you are using an ide you dont need any of this :)
except the python code if you dont have a bunch of txts alredy
