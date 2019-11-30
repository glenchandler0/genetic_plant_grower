from PIL import Image
import sys
import csv

# TODO: Make shell script to run over a bunch of files in a folder and create .pngs to look over

def convertDataToImg(data, xSize, ySzie, filename):
    im= Image.new('RGB', (xSize, ySize))
    pixel_list = []

    for row in data:
        for ch in row:
            if('#' in ch):
                num = int(ch[:-1])
                gVal = 200 - (num * 0.7)
                #G value between 60 and 200
                pixel_list.append((118, int(gVal), 66))
                # print("Found plant!")
            elif(ch == '----'):
                pixel_list.append((174, 242, 237))
                # print("Found NonPlant!")
            elif(ch == ''):
                ch = ch
            else:
                print("Found something weird: ", ch)

    im.putdata(pixel_list)
    im.save('imgs/img_'+filename.replace(".csv", "")+'.png') # Make this correspond to some index

if __name__ == "__main__":
    filename = sys.argv[1]
    xSize = int(sys.argv[2])
    ySize = int(sys.argv[3])
    print("got filename: ", filename, " xSize: ", xSize, " ySize: ", ySize)

    with open(filename, newline='') as csvfile:
        data = list(csv.reader(csvfile, delimiter=' '))
    # print(data)

    convertDataToImg(data, xSize, ySize, filename)
