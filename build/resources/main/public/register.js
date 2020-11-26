const URL = 'http://localhost:8081';

document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#RegisterForm');
    createEntryForm.addEventListener('submit', (e) => register(e));
});

const register = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const register = {};
    register['username'] = formData.get('username');
    register['password'] = formData.get('password');
    fetch(`${URL}/users/sign-up`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            //'Authorization': localStorage.getItem("Token")
        },
        body: JSON.stringify(register)
    }).then((result) => {
        console.log(result)
        if (result.status == 200) {
            window.location.replace(`${URL}/homepage.html`);

        }
    })
}
