import React, { Component } from 'react';

class UserData extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userData: null,
            isLoading: true,
            error: null
        };
    }

    componentDidMount() {
        fetch('/auth/test')
            .then(response => response.json())
            .then(data => {
                this.setState({
                    userData: data,
                    isLoading: false
                });
            })
            .catch(error => {
                this.setState({
                    isLoading: false,
                    error: error
                });
            });
    }

    render() {
        const { userData, isLoading, error } = this.state;

        if (isLoading) {
            return <div>Loading...</div>;
        }

        if (error) {
            return <div>Error: {error.message}</div>;
        }

        return (
            <div>
                <h1>User Data</h1>
                <p>Name: {userData.name}</p>
                <p>Email: {userData.email}</p>
                <p>Age: {userData.age}</p>
            </div>
        );
    }
}

export default UserData;
