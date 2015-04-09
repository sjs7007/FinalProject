from texttable import Texttable

tab = Texttable()
header = ['Matrix', 'Class', 'Hits', 'Most Misses', 'Total Misses']

test_number = 0
i = 232
accuracy = 242
time = 243

with open('/home/nehal/Downloads/GitHub/Results/Cifar_Result_higher_2.txt','rb') as inputFile:
    lines  = inputFile.readlines()
    count = 1
    newlist=[]
    count_test = 1
    print lines[test_number].rstrip('\n')
    print lines[accuracy].rstrip('\n')
    print lines[time].rstrip('\n')
    
    while(count_test < 217):
        l = lines[i].split()
        l = map(int, l)
        m = max(l)
        #index = [a for a, b in enumerate(l) if b == m]
        correct = l[count-1]
        misclass_max = max([l[a] for a,b in enumerate(l) if b != l[count-1]])
        misclass_count = sum([l[a] for a,b in enumerate(l) if b != m])
        index_2 = [a for a, b in enumerate(l) if b == misclass_max]
        
        classSet = {'0':'cat','1':'dog','2':'plane','3':'auto','4':'bird','5':'deer','6':'dog','7':'horse','8':'ship','9':'truck'}
        
        for class_num in index_2:
            if classSet.has_key(str(class_num)):
                index_2[index_2.index(class_num)] = classSet.get(str(class_num)) 

        class_name = classSet.get(str(count-1))
        
        tab.header(header)
        row = [str(l), class_name, str(correct)+"/100", str(misclass_max)+' '+class_name+"-->"+','.join(index_2), str(misclass_count)]
        tab.add_row(row)
        tab.set_cols_width([40,15,15,25,15])
        tab.set_cols_align(['l','l','l','l','l'])
        tab.HEADER

        count += 1
        i += 1
        
        if count == 11:
            s = tab.draw()
            print s
            count_test +=1
            print '\n'*2
            if (count_test < 217):
                count = 1
                tab = Texttable()
                test_number += 245
                i +=235
                accuracy += 245
                time += 245
                print lines[test_number].rstrip('\n')
                print lines[accuracy].rstrip('\n')
                print lines[time].rstrip('\n')
            else:
                break
