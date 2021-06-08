import axios from "axios";

export const insertAnswers = payload => {
    console.log(payload)
    const {
        不太会写这个对象数组怎么传
    } = payload
    return axios.post('${ANSWER_MODULE}/insertAnswers', {
        不太会写这个对象数组怎么传
    }).then(res => {
        return res.data
    })
}
export const getAnswers = payload => {
    console.log(payload)
    return axios.post('${ANSWER_MODULE}/getAnswers/${qnId}?uid=${uid}').then(res => {
        return res.data
    })
}