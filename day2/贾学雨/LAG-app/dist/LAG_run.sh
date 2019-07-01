#!/bin/bash 

function usage() 
{
    echo " Usage : "
    echo "   bash LAG_run.sh deploy initialSupply CreditName CreditSymbol"
    echo "   bash LAG_run.sh getTotalSupply"
    echo "   bash LAG_run.sh balanceOf asset_account"
    echo "   bash LAG_run.sh transfer to_asset_account amount "
    echo " "
    echo " "
    echo "examples : "
    echo "   bash LAG_run.sh deploy 500 LAGC LAG"
    echo "   bash LAG_run.sh getTotalSupply"
    echo "   bash LAG_run.sh balanceOf asset_account "
    echo "   bash LAG_run.sh transfer  Asset1 11111 "
    exit 0
}

    case $1 in
    deploy)
            [ $# -lt 1 ] && { usage; }
            ;;
    getTotalSupply)
            [ $# -lt 1 ] && { usage; }
            ;;
    transfer)
            [ $# -lt 3 ] && { usage; }
            ;;
    balanceOf)
            [ $# -lt 2 ] && { usage; }
            ;;
    *)
        usage
            ;;
    esac

    java -cp 'apps/*:conf/:lib/*' org.fisco.bcos.LAGCredit.client.LAGClient $@

