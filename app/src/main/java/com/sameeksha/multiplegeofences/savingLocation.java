package com.sameeksha.multiplegeofences;

public class savingLocation {

        private double longitude;
        private  double latitude;

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public savingLocation(double longitude, double latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
        }
    }


