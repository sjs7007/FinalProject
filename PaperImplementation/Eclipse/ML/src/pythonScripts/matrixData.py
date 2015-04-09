from texttable import Texttable

tab = Texttable()
i = 232
header = ['Matrix', 'Class', 'Hits', 'Max Miss-->Class', 'Total Miss']

with open('/home/nehal/Downloads/GitHub/Results/copy of Cifar_Result_higher_2.txt','rb') as inputFile:
    lines  = inputFile.readlines()
    count = 1
    newlist=[]
    count_test = 1
    print str(count_test)
    
    while(True):
        l = lines[i].split()
        l = map(int, l)
        
        m = max(l)
        #index = [a for a, b in enumerate(l) if b == m]
        correct = l[count-1]
        misclass_max = max([l[a] for a,b in enumerate(l) if b != l[count-1]])
        misclass_count = sum([l[a] for a,b in enumerate(l) if b != m])
        index_2 = [a for a, b in enumerate(l) if b == misclass_max]
        
        if index_2 == [0]:
            index_2 = " cat "
        elif index_2 == [1]:
            index_2 = " dog "
        elif index_2 == [2]:
            index_2 = " plane "
        elif index_2 == [3]:
            index_2 = " auto "
        elif index_2 == [4]:
            index_2 = " bird "
        elif index_2 == [5]:
            index_2 = " deer "
        elif index_2 == [6]:
            index_2 = " frog "
        elif index_2 == [7]:
            index_2 = " horse "
        elif index_2 == [8]:
            index_2 = " ship "
        elif index_2 == [9]:
            index_2 = " truck "

        class_name = ''
        if count == 1:
            class_name = str('cat')
        elif count == 2:
            class_name = str('dog')
        elif count == 3:
            class_name = str('plane')
        elif count == 4:
            class_name = str('auto')
        elif count == 5:
            class_name = str('bird')
        elif count == 6:
            class_name = str('deer')
        elif count == 7:
            class_name = str('frog')
        elif count == 8:
            class_name = str('horse')
        elif count == 9:
            class_name = str('ship')
        elif count == 10:
            class_name = str('truck')
        
        tab.header(header)
        row = [str(l), class_name, str(correct)+"/100", str(misclass_max)+"-->"+str(index_2), str(misclass_count)]
        tab.add_row(row)
        tab.set_cols_width([40,15,15,25,15])
        tab.set_cols_align(['l','c','c','c','c'])
        tab.HEADER

        count +=1
        i+=1
        if count == 11:
            s = tab.draw()
            print s
            tab = Texttable()
            count_test +=1
            print '' 
            print ''
            print ''
            print count_test
            print ''
            i+=235
            count = 1
