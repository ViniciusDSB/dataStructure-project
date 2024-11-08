import os
import fitz  # PyMuPDF

def pdfToTxt(pdf_folder, txts_folder):

    #Creates the txts folder if not exists
    if not os.path.exists(txts_folder):
        os.makedirs(txts_folder)

    #Loops through all pdfs in pdf folder
    for filename in os.listdir(pdf_folder):
        pdf_path = os.path.join(pdf_folder, filename)
        txt_filename = os.path.splitext(filename)[0] + ".txt"
        txt_path = os.path.join(txts_folder, txt_filename)

        try:
            #Opens the PDF file using PyMuPDF
            pdf_document = fitz.open(pdf_path)
            text = ""

            #Reads all pages in the PDF
            for page_num in range(pdf_document.page_count):
                page = pdf_document.load_page(page_num)
                text += page.get_text()

            #Writes the extracted text to a .txt file
            with open(txt_path, 'w', encoding='utf-8') as txt_file:
                txt_file.write(text)
                
            print(f"Successfully parsed {filename} to {txt_filename}")

        except Exception as e:
            print(f"Deu ruim aqui! {filename}: {e}")

pdf_folder = '../pdfs/'
txts_folder = '../txts'

pdfToTxt(pdf_folder, txts_folder)
