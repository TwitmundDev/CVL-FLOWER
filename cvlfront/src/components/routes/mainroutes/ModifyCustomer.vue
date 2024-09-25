<script >
import axios from "axios";
import Swal from 'sweetalert2'
import {ApiUrl} from '/src/main'
export default {
  data() {
    return{
      customers: {},
      id: "",
      buyerFirstName: "",
      buyerLastName: "",
      buyerClass: "",
      receiverFirstName: "",
      receiverLastName: "",
      receiverClass: "",
      customMessage: "",
      numberOfFlowers: "",
      distributedBy: "",
      didCustomerGetFlower: Boolean,
    }
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      const x = this.$route.params.id;
      axios
          .get(`${ApiUrl}/customers/get/${x}`)
          .then((response) => {

            //console.log(response.data);
            //this.res = JSON.stringify(response.data);
            //this.products = JSON.stringify(response.data);
            //console.log(response.data.customer.id);
            this.customers = response.data.customer;
            this.id = this.customers.id;
            this.buyerFirstName = this.customers.buyerFirstName;
            this.buyerLastName = this.customers.buyerLastName;
            this.buyerClass = this.customers.buyerClass;
            this.receiverFirstName = this.customers.receiverFirstName;
            this.receiverLastName = this.customers.receiverLastName;
            this.receiverClass = this.customers.receiverClass;
            this.customMessage = this.customers.customMessage;
            this.numberOfFlowers = this.customers.numberOfFlowers;
            this.distributedBy = this.customers.distributedBy;
            this.didCustomerGetFlower = this.customers.didCustomerGetFlower;
            console.log("Customer : " + this.customers.id);
          })
          .catch((errors) => {
            console.log(errors); // Errors
          });
    },
    onSubmit() {
      // upload file
      const formData = new FormData()
      formData.append('_id', this.id)
      formData.append('buyerFirstName', this.buyerFirstName)
      formData.append('buyerLastName', this.buyerLastName)
      formData.append('buyerClass', this.buyerClass)
      formData.append('receiverFirstName', this.receiverFirstName)
      formData.append('receiverLastName', this.receiverLastName)
      formData.append('receiverClass', this.receiverClass)
      formData.append('customMessage', this.customMessage)
      formData.append('numberOfFlowers', this.numberOfFlowers)
      formData.append('distributedBy', this.distributedBy)
      formData.append('didCustomerGetFlower',this.didCustomerGetFlower)

      axios.post('http://localhost:8080/api/v1/customers/modify', formData, {}).then((res) => {
        if (res.error === "Bad Request"){
          Swal.fire({
            title: 'Erreur',
            text: "Le formulaire n'est pas conforme",
            icon: 'error',
            confirmButtonText: 'Ok',
            background:"#181818"
          });
        }
        if (res.data.status === "error") {
          Swal.fire({
            title: 'Error!',
            text: `${res.data.message}`,
            icon: 'error',
            confirmButtonText: 'Ok',
            background:"#181818"
          });
        }else {
          Swal.fire({
            icon: 'success',
            title: "Le client a bien été mis a jour",
            showConfirmButton: false,
            background:"#181818",
            timer: 1500,
          });
          setTimeout(() => {
            location.assign(`/`);
          }, 1500);
        }
      });
    },
}
}

</script>

<template>
  <div class="container">
    <form @submit.prevent="onSubmit" id="form">
      <div class="form-group">
        <h3>Acheteur</h3>
        <input id="buyerFirstName" type="text" v-model="buyerFirstName" placeholder="Prénom" class="form-control"  required>
        <input id="buyerLastName" type="text" v-model="buyerLastName" placeholder="Nom" class="form-control" required>
        <h6>Pour la classe merci de respecter cet example: Terminal Sn 1 = TSN1 , Seconde G = 2ndeG</h6>
        <input id="buyerClass" type="text" v-model="buyerClass" placeholder="Classe" class="form-control" required>
      </div>
      <div class="form-group">
        <h3>Destinataire</h3>
        <input id="receiverFirstName" type="text" v-model="receiverFirstName" placeholder="Prénom" class="form-control" required>
        <input id="receiverLastName" type="text" v-model="receiverLastName" placeholder="Nom" class="form-control" required>
        <h6>Pour la classe merci de respecter cet example: Terminal Sn 1 = TSN1 , Seconde G = 2ndeG</h6>
        <input id="receiverClass" type="text" v-model="receiverClass" placeholder="Classe" class="form-control" required>
      </div>
      <div class="form-group">
        <h3>Infos complémentaires</h3>
        <input id="customMessage" type="text" v-model="customMessage" placeholder="Message perso" class="form-control" >
        <input id="numberOfFlowers" type="number" min="1" max="100" v-model="numberOfFlowers" placeholder="Nombre de fleur" class="form-control" required>
        <h6>Distribué par :</h6>
        <select id="distributedBy" v-model="distributedBy" form="form" required>
          <option value="Cvl">Cvl</option>
          <option value="Customer">Acheteur</option>
        </select>
        <h6>A-t-il récuperer les fleurs</h6>
        <select id="didCustomerGetFlower" v-model="didCustomerGetFlower" form="form" required>
          <option value="true">Oui</option>
          <option value="false">Non</option>
        </select>
      </div>
      <div class="form-group">
        <div class="text-center">
          <button class="btn btn-primary btn-block btn-lg">Update Poduct</button>
        </div>
      </div>
    </form>
  </div>

</template>

<style scoped>
h6, h3 {
  text-align: center;
}
select{
  display: block;
  margin: 10px auto;
}
input{
  margin: 5px auto;
}

</style>