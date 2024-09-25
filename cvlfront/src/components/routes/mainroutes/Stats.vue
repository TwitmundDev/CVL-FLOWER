<script>
import axios from "axios";
import {ApiUrl} from "@/main";

export default {
  data() {
    return {
      customers: [],
      overallFlower: 0,
      overallCustomer: 0,
      totalIncome: 0,
    }
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      axios
          .get(`${ApiUrl}/customers/fetch`)
          .then((response) => {
            //console.log(response.data);
            //this.res = JSON.stringify(response.data);
            //this.products = JSON.stringify(response.data);
            this.customers = response.data;
            console.log("Customer : " + this.customers[0].numberOfFlowers);
            this.sortData()
          })
          .catch((errors) => {
            console.log(errors); // Errors
          });
    },
    sortData() {
      this.customers.forEach(customer => {
        //console.log(customer.customMessage)
        this.overallFlower = this.overallFlower + customer.numberOfFlowers;
        this.overallCustomer = this.overallCustomer + 1;
        if (customer.customMessage == "") {
          this.totalIncome = this.totalIncome + (customer.numberOfFlowers * 2.5);
        } else {
          this.totalIncome = this.totalIncome + (customer.numberOfFlowers * 3);
        }
      })
    },

  }
}

</script>

<template>
  <div id="table">
    <table>
      <tr>
        <th>Vente total de fleurs</th>
        <th>Nombre total de clients</th>
        <th>Revenu totaux</th>
      </tr>
      <tr>
        <td>{{ overallFlower }}</td>
        <td>{{ overallCustomer }}</td>
        <td>{{ totalIncome }}€</td>
      </tr>
    </table>
  </div>
</template>

<style scoped>
#table {
  display: flex;
  justify-content: center;
  align-items: center;
  padding-top: 60px;
}

table, caption {
  background-color: #363636;
  align-content: center;
}

td,
th {
  border: 1px solid rgb(190, 190, 190);
  padding: 10px;
}

td {
  text-align: center;
}

h1 {
  text-align: center;
}

</style>