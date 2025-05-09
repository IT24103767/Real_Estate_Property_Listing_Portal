public class QuickSortByPrice {

    public static void quickSort(Product[]arr,int low, int high) {
        if (low < high) {
            int pi = partition(arr,low,high);
            quickSort(arr,low,pi - 1);
            quickSort(arr,pi+1,high);
        }
    }

    private static int partition(Product[] arr,int low,int high) {
        double pivot =arr[high].price;
        int i = low - 1;

        for (int j =  low; j < high; j++) {
            if (arr[j].price <= pivot) {
                i++;
                Product temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Product temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Product[] products = {new Product("House ",55000000.00),
       new Product(" Bedroom Apartment",135000.00),
        new Product("Bare Land",35000000.00),
        new Product("Land with house", 2900000.00),
        new Product("Luxury Fully Furnished Apartment",66500000.00)};

        quickSort(products,0,products.length - 1);

        for (Product p : products){
            System.out.println(p);
        }
    }
}


