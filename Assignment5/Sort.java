class Sort {
    public static void sort(String[] args) {
    //Define a new array of integers
    int[] arr = new int[]{1,2,3,4,5};
    //Print out the original array with formatting
    System.out.println("Original Array: ");
    print1n(arr);
    //sort the array with insertion sort
    insertionSort(arr);
    //Print it out with formatting
    System.out.println("Insertion Sort: ");
    print1n(arr);
    //reset the array of integers
    arr = new int[]{1,2,3,4,5};
    //sort the array using merge sort
    mergeSort(arr);
    //Print it out with formatting
    System.out.println("Merge Sort: ");
    print1n(arr);
    //Reset the array
    arr = new int[]{1,2,3,4,5};
    //quick sort the array
    quickSort(arr);
    //print it out with formatting
    System.out.println("Quick Sort: ");
    print1n(arr);
    //reset the array
    arr = new int[]{1,2,3,4,5};
    //sort the array with the upgraded quick sort and print it out with formatting
    System.out.println("Upgraded Quick Sort: ");
    upgradedQuickSort(arr, 2, 2);
    print1n(arr);
    //reset the array
    arr = new int[]{1,2,3,4,5};
    //select the kth smallest element and print it out
    int k = select(arr, 2);
    System.out.println("K-th Largest");
    System.out.println(k);
    print1n(arr);
    }
    //merge Sort
    public static void mergeSort(int[] a){
    //recurses until length of 'a' is 1
    if (a.length==1){
    return;
    }
    //left array has length of a.length/2
    int[] lA = new int[a.length/2];
    //right array length is a length- left array length
    int[] rA = new int[a.length-lA.length];
    //populates left array
    for (int i = 0; i<a.length/2; i++){
    lA[i] = a[i];
    }
    //populates the right array
    int counter = 0;
    for (int i = a.length/2; i<a.length; i++){
    rA[counter] = a[i];
    counter++;
    }
    //recurses for the left and right arrays
    mergeSort(lA);
    mergeSort(rA);
    //combines the left and right arrays
    merge(lA, rA, a);
    }
    //function to combine two arrays
    public static void merge(int[] l, int[] r, int[] arr){
    //left, right, and general array counters
    int lC = 0;
    int rC = 0;
    int arrC = 0;
    //loops through arr.length times
    while (arrC < arr.length){
    // if the left counter is larger than left length set that index of the array to the right counter's index of the right array
    if (lC >= l.length){
    arr[arrC] = r[rC];
    rC++;
    }
    //opposite of previous
    else if (rC >= r.length){
    arr[arrC] = l[lC];
    lC++;
    }
    //if left of left counter is larger than right of right counter, set array of arrC to left of lC, lC++
    else if (l[lC] >= r[rC]){
    arr[arrC] = l[lC];
    lC++;
    }
    //opposite of previous
    else{
    arr[arrC] = r[rC];
    rC++;
    }
    //array counter ++
    arrC++;
    }
    }
    //insertion sort
    public static void insertionSort(int[] input){
    //double loop to find the largest and moves it to the first index and then checks for the next smallest, and so on
    for (int i=input.length-1; i>0; i--){
    for (int j =i-1;j>=0;j--){
    if (input[j]<input[i]){
    int temp = input[i];
    input[i] = input[j];
    input[j] = temp;
    }
    }
    }
    }
    //quick sort
    public static void quickSort(int[] input){
    //calls another quick sort function just for fun (or so it can recurse)
    quickSort2(input, 0, input.length-1);
    }
    //second quick sort function
    public static void quickSort2(int[] input, int f, int l){
    //if first is larger than last, stop recursing
    if (f>=l){
    return;
    }
    //find the split index using part function
    int split = part(input, f, l);
    //recurse with the first and second part of the array
    quickSort2(input, f, split);
    quickSort2(input, split+1, l);
    }
    //part function used to find the split index
    public static int part(int[] input, int f, int l){
    //pivot @ middlish value
    int pivot = input[(f+l)/2];
    //infinitish loop to keep the function running
    while (true){
    //loops through the ints before the input, continuing until it finds a val that is smaller than the pivot
    while (input[f] > pivot){
    f++;
    }
    //same as above, but opposite
    while (input[l] < pivot){
    l--;
    }
    //if f<l, switch the vals
    if (f<l){
    int temp = input[f];
    input[f] = input[l];
    input[l] = temp;
    }
    //else return
    else {
    return l;
    }
    }
    }
    //upgraded quick sort uses regular quick sort d times and then does merge until subarrays have length of k and then does insertion sort
    public static void upgradedQuickSort(int[] input, int d, int k) {
    //calls another quick sort function for recursion purposes
    myUpgradedQuickSort(input, 0, input.length - 1, d, k);
    }
    //recursion version of upgraded quick sort
    public static void myUpgradedQuickSort(int[] input, int f, int l, int d, int
    k) {
    //if l-f is less than or equal to k, use insertion sort for the rest
    if (l - f <= k) {
    insertionSort(input);
    }
    //if f is greater than or equal to l, stop the recursion
    if (f >= l) {
    return;
    }
    //if d is not reached, continue with quick sort
    if (d > 0) {
    d--;
    int split = part(input, f, l);
    myUpgradedQuickSort(input, f, split, d, k);
    myUpgradedQuickSort(input, split + 1, l, d, k);
    }
    //if d is reached AND, subarrays are larger than k, finish by using mergeSort
    else {
    mergeSort(input);
    }
    }
    //select function finds the kth largest int in the array
    public static int select(int[] input, int k){
    //just does insertion sort k times, making runtime O(kn), where k is a constant
    for (int i=0; i<=k; i++){
    for (int x=i; x<input.length; x++){
    if (input[x]>input[i]){
    int temp = input[i];
    input[i] = input[x];
    input[x] = temp;
    }
    }
    }
    //return the kth largest index
    return input[k];
    }
    //print out the sorted array with formatting
    public static void print1n(int[] arr){
    for (int i = 0; i<arr.length; i++){
    System.out.print(arr[i]+ " ");
    }
    System.out.println();
    System.out.println();
    }
}