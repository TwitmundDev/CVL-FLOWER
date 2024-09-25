<script>
import axios from "axios";
import Swal from 'sweetalert2'
import {ApiUrl} from "@/main";

export default {
  data() {
    return {
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
  methods: {
    onSubmit() {
      // upload file
      const formData = new FormData()
      formData.append('buyerFirstName', this.buyerFirstName)
      formData.append('buyerLastName', this.buyerLastName)
      formData.append('buyerClass', this.buyerClass)
      formData.append('receiverFirstName', this.receiverFirstName)
      formData.append('receiverLastName', this.receiverLastName)
      formData.append('receiverClass', this.receiverClass)
      formData.append('customMessage', this.customMessage)
      formData.append('numberOfFlowers', this.numberOfFlowers)
      formData.append('distributedBy', this.distributedBy)
      formData.append('didCustomerGetFlower',false)

      axios.post(`${ApiUrl}/customers/create`, formData, {}).then((res) => {
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
            title: 'La commande a bien été passer',
            showConfirmButton: false,
            background:"#181818",
            timer: 1500
          })
        }
        //this.status = res.data.status;
        //this.message = res.data.message;
      })
    },
  }
}
</script>

<template>
  <div class="container">
    <form @submit.prevent="onSubmit" id="form">
      <div class="form-group">
        <h3>Acheteur</h3>
        <input type="text" v-model="buyerFirstName" placeholder="Prénom" class="form-control" required>
        <input type="text" v-model="buyerLastName" placeholder="Nom" class="form-control" required>
        <h6>Pour la classe merci de respecter cet example: Terminal Sn 1 = TSN1 , Seconde G = 2ndeG</h6>
        <input type="text" v-model="buyerClass" placeholder="Classe" class="form-control" required>
      </div>
      <div class="form-group">
        <h3>Destinataire</h3>
        <input type="text" v-model="receiverFirstName" placeholder="Prénom" class="form-control" required>
        <input type="text" v-model="receiverLastName" placeholder="Nom" class="form-control" required>
        <h6>Pour la classe merci de respecter cet example: Terminal Sn 1 = TSN1 , Seconde G = 2ndeG</h6>
        <input type="text" v-model="receiverClass" placeholder="Classe" class="form-control" required>
      </div>
      <div class="form-group">
        <h3>Infos complémentaires</h3>
        <input type="text" v-model="customMessage" placeholder="Message perso" class="form-control">
        <input type="number" min="1" max="100" v-model="numberOfFlowers" placeholder="Nombre de fleur" class="form-control" required>
        <!-- <h6>Merci de respecter ce schéma :
             <ul>Par le cvl | mdl = Cvl</ul>
             <ul>Par l'acheteur = Customer</ul>
         </h6>-->
        <h6>Distribué par :</h6>
         <select v-model="distributedBy" form="form" required>
           <option value="Cvl">Cvl</option>
           <option value="Customer">Acheteur</option>
         </select>
         <!--<input type="text" v-model="distributedBy" placeholder="Distribué par" class="form-control" required> -->
      </div>
      <div class="form-group">
        <div class="text-center">
          <button class="btn btn-primary btn-block btn-lg">Create Poduct</button>
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