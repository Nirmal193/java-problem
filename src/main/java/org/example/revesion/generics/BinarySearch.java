package org.example.revesion.generics;

public class BinarySearch {
    public int exactMatch(int arr[], int target){
        int lo = 0, hi = arr.length;
        while(lo<=hi){
            int mid = lo + (hi-lo)/2;
            if(arr[mid] == target) return mid;
            else if(arr[mid] < target) lo = mid + 1;
            else hi = mid-1;
        }
        return -1;
    }
    //first_index > target
    public int upperBound(int arr[],int target){
        int lo = 0,hi = arr.length;
        while(lo<hi){
            int mid = lo + (hi-lo)/2;
            if(arr[mid] < target) lo = mid+1;
            else hi = mid;
        }
        return lo;
    }
    //first_index >= target
    public int lowerBound(int arr[], int target){
        int lo = 0,hi = arr.length;
        while(lo<hi){
            int mid = lo + (hi-lo)/2;
            if(arr[mid]<=target) lo = mid+1;
            else hi = mid ;
        }
        return lo;
    }
    //last_index <= target
    public int lastIndex(int arr[], int target){
        int lo = 0,hi = arr.length;
        while(lo<hi){
            int mid = lo + (hi - lo)/2;
            if(arr[mid] <= target) lo = mid;
            else hi = mid;
        }
        return lo;
    }
    //last_index < target
    public int lastIndex2(int arr[], int target){
        int lo = 0,hi = arr.length;
        while(lo<hi){
            int mid = lo + (hi-lo)/2;
            if(arr[mid] < target) lo = mid;
            else hi = mid;
        }
        return lo;
    }
}
