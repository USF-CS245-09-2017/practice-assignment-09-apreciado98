public class BinaryHeap {
    int[] data = new int[100];
    int size=0;

    public void add(int item){
        if(data.length<=size){ //check size
            grow_array();
        }
        data[size++]=item;
        int current = size-1;
        int parent = (current-1)/2;
        while(data[current]<data[parent] && current!=0){
            swap(data, current, parent);
            current=parent;
            parent=(parent-1)/2;
        }
    }

    public int remove(){
        if(size==0){
            throw new ArrayIndexOutOfBoundsException();
        }
        swap(data, 0, size-1); //swapping the root for the last child
        --size;
        if(size>0){
            shiftdown(0); //take the root, take lesser of its two children and then compare the root to that 			//child and  potentially swap the two
        }
        return data[size];
    }

    public void shiftdown(int pos){
        int left = 2*pos+1; //left child
        int right = 2*pos+2; //right child
        if(left>=size || right>=size){
            return;
        }
        //finding the smallest child
        if(data[left]<data[right] && data[left]<data[pos]){
            swap(data,left,pos); //swap with parent
            shiftdown(left); //recursive call
        }
        else if(data[left]>data[right] && data[right]<data[pos]) {
            swap(data, right, pos); //swap with parent
            shiftdown(right); //recursive call
        }
    }

    protected void swap(int[] arr, int pos1, int pos2){
        int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }

    protected void grow_array(){
        int[] temp = new int[data.length*2];
        for(int i=0; i<data.length;i++){
            temp[i]=data[i];
        }
        data=temp;
    }
}