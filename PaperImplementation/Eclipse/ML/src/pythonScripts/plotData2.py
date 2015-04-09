import matplotlib as mpl
import matplotlib.pyplot as plt
import pandas as pd
import collections
import csv

data = pd.read_csv("/home/nehal/Documents/Accuracy_Cifar1296.csv")
data.columns = ["N", "C_h", "C_s", "C_v", "Accuracy"]

cH = list(data.C_h)
cS = list(data.C_s)
cV = list(data.C_v)
n = list(data.N)
acc = list(data.Accuracy)

fig = plt.figure()
fig.suptitle('N vs Accuracy [CIFAR-10]')

temp1 = []
temp2 = []
for x in range(1,len(cH)):
    if cH[x]==10:
        if cS[x]==10:
            if cV[x]==10:
                print n[x],acc[x]
                temp1.append(n[x])
                temp2.append(acc[x])
dictionary = dict(zip(temp1, temp2))
od = collections.OrderedDict(sorted(dictionary.items()))
ax1 = fig.add_subplot(221)
ax1.set_title('C_H=10 C_S=10 C_V=10', fontsize=12)
ax1.set_xlabel('N')
ax1.set_ylabel('Accuracy')
ax1.plot(od.keys(),od.values(), linestyle='--', marker='o', color='r',label='C_H=10 | C_S=10 | C_V=10')


temp3 = []
temp4 = []
for x in range(1,len(cH)):
    if cH[x]==10:
        if cS[x]==8:
            if cV[x]==8:
                print n[x],acc[x]
                temp3.append(n[x])
                temp4.append(acc[x])
dictionary = dict(zip(temp3, temp4))
od = collections.OrderedDict(sorted(dictionary.items()))                
ax2 = fig.add_subplot(222)
ax2.set_title('C_H=10 C_S=8 C_V=8', fontsize=12)
ax2.set_xlabel('N')
ax2.set_ylabel('Accuracy')
ax2.plot(od.keys(),od.values(), linestyle='--', marker='o', color='g',label='C_H=10 | C_S=8 | C_V=8')

temp5 = []
temp6 = []
for x in range(1,len(cH)):
    if cH[x]==10:
        if cS[x]==6:
            if cV[x]==6:
                print n[x],acc[x]
                temp5.append(n[x])
                temp6.append(acc[x])
dictionary = dict(zip(temp5, temp6))
od = collections.OrderedDict(sorted(dictionary.items()))
ax3 = fig.add_subplot(223)
ax3.set_title('C_H=10 C_S=6 C_V=6', fontsize=12)
ax3.set_xlabel('N')
ax3.set_ylabel('Accuracy')
ax3.plot(od.keys(),od.values(), linestyle='--', marker='o', color='b',label='C_H=10 | C_S=6 | C_V=6')

plt.show()



