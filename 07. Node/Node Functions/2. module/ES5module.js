import { odd, even } from './var';

function checkOddOrEven(num){
    if(num % 2){
        return odd;
    }
    return even;
}

export default checkOddOrEven;

// require -> import
// module.export -> export default