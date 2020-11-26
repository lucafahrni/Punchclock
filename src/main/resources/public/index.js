const URL = "http://localhost:8081"

document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#createLoginForm');
    createEntryForm.addEventListener('submit', (e) => login(e));
});

const login = (e) =>{
    e.preventDefault();
    const formData = new FormData(e.target);
    const login = {};
    login['username'] = formData.get('username');
    login['password'] = formData.get('password');

    fetch(`${URL}/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(login)
    }).then((result) => {
        console.log(result)
        if (result.status == 200){
        localStorage.setItem("Token", result.headers.get("Authorization"));
            window.location.replace(`${URL}/homepage.html`);

    }

    });
    }
