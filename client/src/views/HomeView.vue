<template>
    <div class="title">
        <div class="animation">
            <h1>Welcome to my link shortener!</h1>
        </div>
        <h2>To use this tool, simply enter your link below and press ENTER</h2>
    </div>
    <div class="form-wrapper">
        <div v-if="submitted" class="message">    
            <h3>Link Created!</h3>
            <p>
                <a v-bind:href="linkCreated" target="_blank" >{{ linkCreated }}</a>
            </p>
        </div>
        <div v-if="errors.message != ''" class="message">    
            <h3>Error!</h3>
            <p>{{ errors.message }}</p>
        </div>
        <form @submit.prevent="handleSubmit" autocomplete="off">
            <div class="input">
                <label for="link">Full Link</label>
                <input
                 type="text"
                 id="linkTo"
                 placeholder="https://yanramos.com.br/folder1/folder2/yanramos.pdf"
                 :class="{'error': errors.linkTo}"
                 v-model="formData.linkTo"
                 required>
            </div>

             <div class="customURIMessage" :onclick="displayInput">Want to customize your shortened link? <span>Click here</span></div>
             
            <div :class="['input',{'hidden': isHidden}]">
                <label for="shortenedLink">Custom Link</label>
                <input
                 type="text"
                 id="shortenedLink"
                 maxlength="20"
                 placeholder="resume => https://ls.yanramos.com.br/in/resume"
                 :class="{'error': errors.shortenedLink}"
                 v-model="formData.shortenedLink">
                </div>

              <button type="submit">Submit</button>
        </form>
    </div>
</template>
<script lang="ts">
import { Vue } from 'vue-class-component';
import axios from 'axios';
const env = process.env;

export default class HomeView extends Vue {
    isHidden = true;
    linkCreated = "";
    formData = {
        linkTo: "",
        shortenedLink: "",
    }
    errors = {
        linkTo: false,
        shortenedLink: false,
        message: "",
    }
    submitted = false;

    displayInput() {
        this.isHidden == false ? this.isHidden = true : this.isHidden = false;
    }
    sanitizedShortenedLink() {
        return this.formData.shortenedLink.replace(/[^a-zA-Z0-9]/g, '');
    }

    async handleSubmit() {
        this.submitted = false;
        this.linkCreated = "";
        this.errors = {
        linkTo: false,
        shortenedLink: false,
        message: ""};

        if(!this.formData.linkTo) return this.errors.linkTo = true;
        
        try {
            this.formData.shortenedLink = this.sanitizedShortenedLink();
            const response = await axios.post(`${env.VUE_APP_API_DOMAIN}/in/`, this.formData);
            
            if(response.status == 201) {
                this.submitted = true;
                this.linkCreated = `${env.VUE_APP_API_DOMAIN}/in/`+response.data.shortenedLink;
                this.formData = {
                    linkTo: "",
                    shortenedLink: "",
                }
            }
        } catch (error: unknown) {
            if (error instanceof Error) {
                this.errors.message = "Change the custom link";
            } else {
                this.errors.message = "Unknown error";
            }
        }
    }
}

</script>
<style lang="scss" scoped>
@import '@/sass/pages/home.scss';
</style>