//Use this to extract small cells out of complete matrix
// working

class testExtractCells
{
    public static void main(String args[])
    {
        int temp[][]={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        int cs=2;
        int temp2[][]=new int[cs][cs];
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++)
            {
                for(int p=i*cs;p<(i+1)*cs;p++)
                {
                    for(int q=j*cs;q<(j+1)*cs;q++)
                    {
                        //System.out.println(i+" "+j+" "+p+" "+q);
                        temp2[p-(i*cs)][q-(j*cs)]=temp[p][q];
                    }
                }
                
                for(int x=0;x<2;x++)
                {
                    for(int y=0;y<2;y++)
                    {
                        System.out.print(temp2[x][y]+" ");
                    }
                    System.out.println();
                }
            }
            
           
        }
    }  
}

/* 

1 2 3 4 
5 6 7 8 
9 10 11 12 
13 14 15 16

Output : 

1 2 
5 6 

3 4 
7 8 

9 10 
13 14 

11 12 
15 16 
*/