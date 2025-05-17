
public class Rent extends Property {
        private double pricePerMonth;
        private double pricePerNight;
        private int minRentalDays;



        public Rent(String location,
                    double size, double price, String description,
                    String ownerName, boolean isAvailable, String imagePath,String userId, double pricePerMonth, double pricePerNight, int minRentalDays) {


            super(location, size,price, description, ownerName, isAvailable, imagePath, userId);

            this.pricePerMonth = pricePerMonth;
            this.pricePerNight = pricePerNight;
            this.minRentalDays = minRentalDays;
        }

        public Rent() {}


        public void setPricePerMonth(double pricePerMonth) {
            this.pricePerMonth = pricePerMonth;
        }
        public void setPricePerNight(double pricePerNight) {
            this.pricePerNight = pricePerNight;
        }
        public void setMinRentalDays(int minRentalDays) {
            this.minRentalDays = minRentalDays;
        }

        public double getPricePerMonth() { return pricePerMonth; }
        public double getPricePerNight() { return pricePerNight; }
        public int getMinRentalDays() { return minRentalDays;}

    }

